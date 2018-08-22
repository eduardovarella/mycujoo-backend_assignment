# MyCujoo Backend Position Assignment

Assignment projetct for MyCujoo backend position.

This application reads schema information from an API and generates corresponding CREATE TABLE commands.

## Assumptions
1) The create table commands are targeting MySQL 5.7
2) Subject names are being used as table names, replacing "-" character with "_"
3) Every table will have the first column as primary key
4) "string" field type is being converted to "varchar(255)"
5) Java and Gradle are already installed and available in the PATH environment variable

## Installation
```bash
git clone https://github.com/eduardovarella/mycujoo-backend_assignment.git
cd mycujoo-backend_assignment
gradle build
```

## Configuration

Edit conf.yml file to configure application parameters
```bash
# API endpoint to load subjects list
subjectsURL: https://s3-eu-west-1.amazonaws.com/mycujoo-assignments/be-assignment/subjects.json

# API endpoint prefix to load schema definition
# Will be used as follows: <schemaURLPrefix><subject-name>.json
schemaURLPrefix: https://s3-eu-west-1.amazonaws.com/mycujoo-assignments/be-assignment/

# Output file path
outputFile: commands.sql
```

## Running Tests
```bash
gradle test
```

## Running Application
```bash
gradle run --args 'conf.yml'
```
