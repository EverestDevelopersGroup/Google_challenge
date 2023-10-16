import cv2
import numpy as np

def save_image_to_txt(image, output_path):
    # If the image doesn't have an alpha channel, add one
    if image.shape[2] == 3:
        b, g, r = cv2.split(image)
        alpha_channel = np.ones(b.shape, dtype=b.dtype) * 255  # Creating a dummy alpha channel image.
        image = cv2.merge((b, g, r, alpha_channel))

    with open(output_path, 'w') as f:
        # Save the image's height and width as the first two lines in the .txt file
        f.write(f"{image.shape[0]}\n")
        f.write(f"{image.shape[1]}\n")
        # Save the image data
        np.savetxt(f, image.reshape(-1, image.shape[2]), fmt='%d', delimiter=',', comments='')



    print(f"Array saved to '{output_path}'")

if __name__ == "__main__":
    # Load the image
    image_path = 'Image'  # replace with your image path
    image = cv2.imread(image_path, cv2.IMREAD_UNCHANGED)  # cv2.IMREAD_UNCHANGED preserves the alpha channel

    output_path = '/home/atamuratov_e/Desktop/LOGO/Image Experiment/output_array.txt'
    save_image_to_txt(image, output_path)

