# Github CLI App

This is a Spring Boot CLI application for managing GitHub repositories.

## Features
- Fetch repository names
- Create new repositories
- Create repositories from templates
- Delete repositories
- Add files to repositories

## Prerequisites
- Java 21
- Gradle
- GitHub Personal Access Token

## Setup
1. Set your GitHub token in `src/main/resources/application.properties`
2. Install Gradle wrapper: `./install-gradle.sh`
3. Build the app: `./run.sh build`

## Usage
Run commands using the run script: `./run.sh <command>`

```
# List repositories
./run.sh fetch

# Create repository
./run.sh create my-repo true

# Create from template
./run.sh template template-repo new-repo

# Delete repository
./run.sh delete my-repo

# Add file
./run.sh add-file my-org my-repo README.md
```