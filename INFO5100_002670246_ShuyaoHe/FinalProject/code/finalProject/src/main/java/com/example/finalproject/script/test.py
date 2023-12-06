import argparse
import os
import sys
from PIL import Image
import torch
import numpy as np
from numRecognizer import ImageClassifierNet
from torchvision import transforms

# Preprocess the image to match the input requirements of the neural network model
def load_image(path_to_image):
    image = Image.open(path_to_image).convert("L")  # convert("L") will make the graph in black and white
    image = image.resize((28, 28))
    image = np.array(image)
    image = image/255.0
    image = 1-image
    image = image.astype("float32")
    return image

# Convert the numpy array to a PyTorch tensor and normalize it
transform = transforms.Compose([transforms.ToTensor(), transforms.Normalize((0.1307), (0.3081))])

if __name__ == "__main__":
    path_to_image = sys.argv[1]
    image_path = os.path.dirname(path_to_image)
    script_path = image_path + "/../script/"

    # Loads and transforms the image using the load_image function and the defined PyTorch transform
    image = load_image(path_to_image)
    image = transform(image)

    # Initializes the ImageClassifierNet model
    model = ImageClassifierNet()
    # Loads the model weights from a file
    model.load_state_dict(torch.load(script_path+"model.pth"))
    # Sets the model to evaluation mode
    model.eval()
    # Performs inference on the transformed image and prints the result
    print("%i"%int(torch.argmax(model(image))))