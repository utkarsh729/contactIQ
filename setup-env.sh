#!/bin/bash

# Create .env file from template if it doesn't exist
if [ ! -f .env ]; then
    echo "Creating .env file..."
    cp .env.template .env
    echo "Please update the .env file with your credentials"
fi

# Create application.properties from template if it doesn't exist
if [ ! -f src/main/resources/application.properties ]; then
    echo "Creating application.properties..."
    cp src/main/resources/application.properties.template src/main/resources/application.properties
    echo "Please update the application.properties file with your credentials"
fi

echo "Setup complete! Please update the configuration files with your credentials." 