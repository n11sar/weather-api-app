# Spring Boot Weather Application

## Overview
This Spring Boot application integrates with a third-party weather API to retrieve and display weather data. Users can search for weather data by zip code or city name. The application uses Hibernate to persist weather data in a local PostgreSQL database and employs Spring Scheduler to periodically refresh the weather data.

## Prerequisites
- Java JDK 11 or later
- Gradle
- PostgreSQL

## Setup
1. **Clone the Repository:**
   ```bash
  
   git clone https://github.com/n11sar/weather-api-app.git
   ```

2. **Configure Database and API Key:**
   Open the `application.properties` file located in `src/main/resources` and update the following properties with your details:
   ```
   spring.datasource.url=jdbc:postgresql://localhost:5432/weather_api
   spring.datasource.username=[Your Database Username]
   spring.datasource.password=[Your Database Password]
   spring.jpa.hibernate.ddl-auto=update
   weather.api.key=[Your Weather API Key]
   ```

3. **Build the Application:**
   ```bash
   ./gradlew build
   ```

4. **Run the Application:**
   ```bash
   ./gradlew bootRun
   ```

## Configuring the Scheduler and City List
- **Set Scheduler Time:** The scheduler is set to run every hour. To change this, update the `fixedRate` value in `WeatherDataScheduler.java` (3600000ms = 1 hour).
- **List of Cities for Weather Updates:** The default cities are London, New York, and Tokyo. Modify the `getCityNames()` method in `WeatherDataScheduler.java` to add or remove cities.

## Using the Application
Access the application through your web browser or API client at `http://localhost:8080`. Search for weather data by providing a zip code or city name.
