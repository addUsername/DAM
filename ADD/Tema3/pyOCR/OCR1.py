# -*- coding: utf-8 -*-
"""
Created on Mon Jan 18 02:39:29 2021

@author: SERGI
"""
import os
from pdf2image import convert_from_path

print("Reading file")
filename = ""
# Store Pdf with convert_from_path function
images = convert_from_path(filename, output_folder=(
    os.path.join(os.getcwd(), "temp")), grayscale=(True))

print("images will be saved in: " + os.path.join(os.getcwd(), "output"))
print("imgs length: "+str(len(images)))

# crop img
width, height = images[0].size
top = int(height*0.2)  # ok
left = int(width*0.27)  # ok
right = int(width*0.85)  # ok
bottom = int(height*0.45)  # ok

# should resize?

print("image size: ")
print(left, top, right, bottom)

# Save img
i = 2
for img in images:

    # tuple!! (left, top, right, bottom)
    img = img.crop((left, top, right, bottom))
    name = "Q"+str(i) if (i % 2 == 0) else "A"+str(i-1)
    name += ".jpeg"
    img.save(os.path.join(os.getcwd(), "output", name), 'JPEG')
    i += 1

print("Finish")
