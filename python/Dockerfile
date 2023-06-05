FROM python:3.8-slim-buster

EXPOSE 5000

# Set the working directory in the container
WORKDIR /application

# Copy the requirements file into the container
COPY requirements.txt .

# Install the Python dependencies
RUN pip install --upgrade pip
RUN pip install --no-cache-dir -r requirements.txt

# Copy the rest of the application code into the container
COPY . .

# Set the entry point command to run the Flask application
CMD ["python", "run.py"]
