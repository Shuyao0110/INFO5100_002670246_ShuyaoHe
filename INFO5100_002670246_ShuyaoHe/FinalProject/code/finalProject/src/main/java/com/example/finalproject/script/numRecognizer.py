import torch
from matplotlib import pyplot as plt
from torch.utils.data import Dataset, DataLoader
import numpy as np
from torchvision import datasets
from torchvision import transforms
import os
from torch.utils.data.sampler import SubsetRandomSampler
from torch.utils.data.dataloader import DataLoader
from torchvision.utils import make_grid
import torch.nn as nn
import torch.nn.functional as F
from torchinfo import summary

def show_batch(dl):
    for images, labels in dl:
        fig, ax = plt.subplots(figsize=(10, 10))
        ax.set_xticks([])
        ax.set_yticks([])
        ax.imshow(make_grid(images, 8).permute(1, 2, 0), cmap="Greys_r")
        break

class ImageClassifierNet(nn.Module):
    def __init__(self, n_channels=3):
        super(ImageClassifierNet, self).__init__()
        # The sequential function executes the parameters inside it in order.
        self.conv1 = nn.Sequential(
            # conv2d first parameter: the dimension of RGB, 1 for black and white images.
            # conv2d second parameter: the feature for convolution.
            # conv2d third parameter: kernel, the side length of the convolution kernel: odd number, based on image size and precision.
            # conv2d fourth parameter: stride of the kernel, generally 1.
            # padding is generally (kernel size - 1) / 2.
            # 10: the feature for convolution;
            # 28*28: pixel.
            nn.Conv2d(1, 10, 3, 1, padding=1),  # After conv2d, output becomes (10, 28, 28).
            # Non-linear layer, zeros out the negatives, keeps the positives unchanged.
            nn.ReLU(),
            # Max pooling.
            nn.MaxPool2d(2, 2),  # After max pooling (10, 14, 14).
        )

        self.conv2 = nn.Sequential(
            nn.Conv2d(10, 30, 3, 1, padding=1),  # (30, 14, 14)
            nn.ReLU(),
            nn.MaxPool2d(2, 2),  # (30, 7, 7)
        )

        self.linear1 = nn.Sequential(
            # Flatten a 30*7*7 three-dimensional matrix into a 60*1 column vector.
            nn.Linear(30 * 7 * 7, 60),
            # Selectively drop out some neurons, 0.3, 0.5 are both possible.
            nn.Dropout(0.2),
            nn.ReLU(),
        )
        # Compress into a 10*1 column vector, as there are 10 digits.
        self.linear2 = nn.Linear(60, 10)

    def forward(self, X):
        X = self.conv1(X)
        X = self.conv2(X)
        X = X.view(-1, 30 * 7 * 7)
        X = self.linear1(X)
        X = F.log_softmax(self.linear2(X), dim=1)
        return X

def get_default_device():
    if torch.backends.mps.is_available():
        return torch.device("mps")
    elif torch.cuda.is_available():
        return torch.device("cuda")
    else:
        return torch.device("cpu")

def to_device(data, device):
    """Move tensor(s) to chosen device"""
    if isinstance(data, (list, tuple)):
        return [to_device(x, device) for x in data]
    return data.to(device, non_blocking=True)

class DeviceDataLoader:
    """Wrap a dataloader to move data to a device"""
    def __init__(self, dl, device):
        self.dl = dl
        self.device = device

    def __iter__(self):
        """Yield a batch of data after moving it to device"""
        for b in self.dl:
            yield to_device(b, self.device)

    def __len__(self):
        """Number of batches"""
        return len(self.dl)

def train_model(n_epochs, model, train_dl, val_dl, loss_fn, opt_fn, lr):
    """
    Trains the model on a dataset.

    Args:
        n_epochs: number of epochs
        model: ImageClassifierNet object
        train_dl: training dataloader
        val_dl: validation dataloader
        loss_fn: the loss function
        opt_fn: the optimizer
        lr: learning rate

    Returns:
        The trained model.
        A tuple of (model, train_losses, val_losses, train_accuracies, val_accuracies)
    """
    # Record these values the end of each epoch
    train_losses, val_losses, train_accuracies, val_accuracies = [], [], [], []
    params = filter(lambda p: p.requires_grad, model.parameters())
    optimizer = opt_fn(params, lr=lr)
    for i in range(n_epochs):
        model.train()
        sum_loss, correct, total = 0, 0, 0
        for x, y in train_dl:
            x, y = x.to(device), y.to(device)
            y_ = model(x)
            _, pred = torch.max(y_, 1)
            optimizer.zero_grad()
            loss = loss_fn(y_, y)
            loss.backward()
            optimizer.step()
            sum_loss += loss.item() * y.shape[0]
            correct += (pred == y).float().sum()
            total += y.shape[0]
        val_loss, val_acc = evaluate(model, val_dl, loss_fn)
        train_loss = sum_loss / total
        train_acc = float(correct) / total
        train_losses.append(train_loss)
        val_losses.append(val_loss)
        train_accuracies.append(train_acc)
        val_accuracies.append(val_acc)

        print(f'Epoch [{i+1}/{n_epochs}], Train Loss: {train_loss:.4f}, Train Accuracy: {train_acc:.4f}, Val Loss: {val_loss:.4f}, Val Accuracy: {val_acc:.4f}')

    return model, train_losses, val_losses, train_accuracies, val_accuracies

def evaluate(model, val_dl, loss_fn):
    if len(val_dl) == 0:
        return 0, 0
    model.eval()
    correct, total, sum_loss = 0, 0, 0
    for x, y in val_dl:
        x, y = x.to(device), y.to(device)
        y_ = model(x)
        _, pred = torch.max(y_, 1)
        loss = loss_fn(y_, y)
        correct += (pred == y).float().sum()
        total += y.shape[0]
        sum_loss += loss.item() * y.shape[0]
    return float(sum_loss) / total, float(correct) / total

if __name__ == "__main__":
    transform = transforms.Compose(
        [transforms.ToTensor(), transforms.Normalize((0.1307), (0.3081))]
    )
    train_dataset = datasets.MNIST(
        root="./data/mnist", train=True, download=True, transform=transform
    )
    test_dataset = datasets.MNIST(
        root="./data/mnist", train=False, download=True, transform=transform
    )
    print(train_dataset[0][0].shape)
    print(train_dataset.classes)

    batch_size = 16
    # Training sampler and data loader
    train_dl = DataLoader(train_dataset, batch_size)

    # Validation sampler and data loader
    val_dl = DataLoader(test_dataset, batch_size)
    model = ImageClassifierNet()
    device = get_default_device()
    print(device)

    to_device(model, device)

    num_epochs = 30  # Max number of training epochs
    loss_fn = F.nll_loss  # Define the loss function
    opt_fn = torch.optim.Adam  # Select an optimizer
    lr = 1e-4  # Set the learning rate

    history = train_model(num_epochs, model, train_dl, val_dl, loss_fn, opt_fn, lr)
    model, train_losses, val_losses, train_accuracies, val_accuracies = history
    torch.save(model.state_dict(), 'model_test.pth')
    print(test_dataset[0])
    plt.imshow(test_dataset[0][0].squeeze(0))
    torch.argmax(model(test_dataset[0][0]))