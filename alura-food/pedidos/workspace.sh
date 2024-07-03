#!/bin/bash
dockerComposeFile="docker-compose.yml"
projectName="pedidos-ms"

while true; do
    echo "Select an option:"
    echo "1) Start Docker Compose services"
    echo "2) Stop Docker Compose services"
    echo "3) Restart Docker Compose services"
    echo "4) View status of Docker Compose services"
    echo "5) Exit"
    read -p "Enter your choice [1-5]: " choice

    case $choice in
        1)
            echo "Starting Docker Compose services..."
            docker compose  -p "$projectName" -f "$dockerComposeFile" up -d
            ;;
        2)
            echo "Stopping Docker Compose services..."
            docker compose  -p "$projectName" -f "$dockerComposeFile" down --remove-orphans --rmi local
            ;;
        3)
            echo "Restarting Docker Compose services..."
            docker compose  -p "$projectName" -f "$dockerComposeFile" down --remove-orphans --rmi local
            docker compose  -p "$projectName" -f "$dockerComposeFile" up -d
            ;;
        4)
            echo "Docker Compose services status:"
            docker compose  -p "$projectName" -f "$dockerComposeFile" ps
            ;;
        5)
            echo "Exiting..."
            exit 0
            ;;
        *)
            echo "Invalid option, please try again."
            ;;
    esac
done
