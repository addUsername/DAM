# -*- coding: utf-8 -*-
"""
Created on Mon Jan 18 17:05:09 2021

@author: SERGI

https://www.geeksforgeeks.org/python-reading-contents-of-pdf-using-ocr-optical-character-recognition/
"""
import pytesseract
import os
import re
from xml.dom import minidom
from PIL import Image
import xml.etree.ElementTree as ET
import time

folder = os.path.join(os.getcwd(), "output")


# get image path
def getImagesPath(index):
    imgQ = os.path.join(folder, "Q"+str(index)+".jpeg")
    imgA = os.path.join(folder, "A"+str(index)+".jpeg")
    return (imgQ, imgA)

# do magic ABCDEFGHIJKLMNNOPQRSTUVWXYZ
def doMagicRaw(imgQ, imgA):

    '''
    not needed
    text = pytesseract.image_to_string(Image.open(imgQ), config="--psm 11 --oem 3", lang="spa")
    text = pytesseract.image_to_string(Image.open(imgQ),config="-c tessedit_char_whitelist=#.·=!0123456789ABCDEFGHIJKLMNNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz")
    '''
    text = pytesseract.image_to_string(Image.open(imgQ))
    textAnswer = pytesseract.image_to_string(Image.open(imgA))
    return (text, textAnswer)

# get output
def parseOutput(text, textAnswer):
    
    questionsList = [i for i in text.split("\n") if (i != '') and (i != ' ') ]
    answersList = [i for i in textAnswer.split("\n") if (i != '') and (i != ' ') ]
    return (questionsList, answersList)

def shouldJoinQuestion(question):

    if (re.match("^[A-Z#].*$", question) != None):
        return True

    return False

def shouldJoinAnswer(answer):
    if (re.match("^[a-z]*,.*$", answer) == None):
        return True
    if (re.match("^[A-Z#].*$", answer) != None):
        return True

    return False


# process output
def cleanOutput(questionsList, answersList):
    questions = ["", "", ""]
    index = 0
    
    try:
        for i in range(3):
    
            if (index > len(questionsList)):
                questions[i] = questionsList[-1]
                index +=1
            elif (shouldJoinQuestion(questionsList[index+1][0])):
                questions[i] = questionsList[index]
                index += 1
            else:
                questions[i] = questionsList[index] + " " + questionsList[index+1]
                index += 2
    
        answers = ["", "", ""]
        index = 0
    
        for i in range(3):
    
            if (index > len(answersList)):
                answers[i] = answersList[-1]
                index +=1
            elif (shouldJoinAnswer(answersList[index+1][0])):
                answers[i] = answersList[index]
                index += 1
            else:
                answers[i] = answersList[index] + " " + answersList[index+1]
                index += 2
    
        return (questions, answers)
    except:
        return (None, None)

# Fuck unicode or whichever invalid token is in there.
# TODO Check if str.encode().decode() 

def createQuestionElement(root, questions, answers):

    if(answers == None):
        print("Skip")
        return
    
    print(questions)
    print(answers)

    for i in range(3):
        questionXml = ET.SubElement(root, "pregunta")

        textXml = ET.SubElement(questionXml, "texto")
        if re.match( "[a-zA-Z]",questions[i][-1]):
           textXml.text = questions[i]
        else:
            textXml.text = questions[i][:-1]

        ans0 = ET.SubElement(questionXml, "respuesta1")
        ans0.text = answers[0]

        ans1 = ET.SubElement(questionXml, "respuesta2")
        ans1.text = answers[1]

        ans2 = ET.SubElement(questionXml, "respuesta3")
        ans2.text = answers[2]

        sol = ET.SubElement(questionXml, "correcta")
        sol.text = str(i)

def main():

    start = time.time()

    # create xml root
    root = ET.Element("juego")
    root.append(ET.Comment("Generated by OCR2_1.py for add.dam2.p3.MainModel.java"))

    for i in range (2,115,2):
        print("total: "+ str(i/2))
        imgQ, imgA = getImagesPath(i)
        text, textAnswer = doMagicRaw(imgQ,imgA)
        
        questionsList, answersList = parseOutput(text, textAnswer)
        questions, answers = cleanOutput(questionsList, answersList)
        createQuestionElement(root, questions, answers)

    xml = ET.ElementTree(root)
    # Generate String
    raw_string = ET.tostring(root, method='xml')
    string = "<?xml version='1.0' encoding='utf-8'?>"+str(raw_string,encoding='utf-8')
    
    parsed_string = minidom.parseString(string)
    fileText = parsed_string.toprettyxml(indent="\t")    

    # Save
    f = open("preguntas.xml", "w")
    f.write(fileText)
    f.close()
    print(fileText)
    end = time.time()
    print("finish! it tooks:" + str(end - start))

if __name__ == "__main__":
    main()
