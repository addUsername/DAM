# -*- coding: utf-8 -*-
"""
Created on Mon Jan 18 02:39:29 2021
TODO: delete temp folder!
@author: SERGI
"""
import os
from pdf2image import convert_from_path

print("Reading file")
print("images will be saved in: " + os.path.join(os.getcwd(), "output"))
# Store Pdf with convert_from_path function
images = convert_from_path('Trivial.pdf', output_folder=(
    os.path.join(os.getcwd(), "temp")), grayscale=(True))


print("imgs length: "+str(len(images)))

# crop img
width, height = images[0].size
top = int(height*0.19)  # ok
left = int(width*0.268)  # ok
right = int(width*0.82)  # ok
bottom = int(height*0.448)  # ok

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
