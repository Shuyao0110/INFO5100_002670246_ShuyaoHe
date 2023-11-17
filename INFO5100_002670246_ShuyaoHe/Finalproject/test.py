import argparse
import sys

def main():
    parser = argparse.ArgumentParser()
    parser.add_argument("--imagePath", type=str, required=True)
    parser.add_argument("--test", type=str, required=True)
    args = parser.parse_args()
    print(args.imagePath)
    print(args.test)

def main1():
    print(sys.argv)
if __name__ == "__main__":
    main1()