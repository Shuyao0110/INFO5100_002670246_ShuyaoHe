# import argparse
# import sys

# def main():
#     parser = argparse.ArgumentParser()
#     parser.add_argument("--imagePath", type=str, required=True)
#     parser.add_argument("--test", type=str, required=True)
#     args = parser.parse_args()
#     print(args.imagePath)
#     print(args.test)

# def main1():
#     print(sys.argv)
# if __name__ == "__main__":
#     main1()
import argparse
import os
import sys
from PIL import Image
import torch
import numpy as np
from numRecognizer import ImageClassifierNet
from torchvision import transforms

# import cv2


def load_image(path_to_image):
    image = Image.open(path_to_image).convert("L")
    return image


transform = transforms.Compose(
    [transforms.ToTensor(), transforms.Normalize((0.1307), (0.3081))]
)

if __name__ == "__main__":
    path_to_image = sys.argv[1]
    print(path_to_image)
    image_path = os.path.dirname(path_to_image)
    print(path_to_image)
    script_path = image_path + "\\..\\script\\"
    image = load_image(path_to_image)
    image = image.resize((28, 28))
    image = np.array(image)

    image = transform(image)
    print(image.shape)
    model = ImageClassifierNet()
    model.load_state_dict(torch.load("model.pth"))
    model.eval()
    print(int(torch.argmax(model(image)).item()))
