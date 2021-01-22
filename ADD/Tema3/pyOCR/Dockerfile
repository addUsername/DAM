FROM continuumio/miniconda3

COPY . /app
WORKDIR /app

RUN apt update
RUN apt -y install tesseract-ocr

RUN conda create --name myenv python=3.7
RUN conda run -n myenv pip install pytesseract
RUN conda run -n myenv pip install pdf2image
RUN conda run -n myenv conda install -c conda-forge poppler
RUN conda run -n myenv pip install pillow

CMD ["sh","-c","echo creando images && conda run -n myenv python OCR1.py && echo procesando images && conda run -n myenv python OCR2_1.py"]