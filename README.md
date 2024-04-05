# Rick and Morty Universe Explorer

Explore the vast universe of Rick and Morty with this simple Android application designed to showcase Android architecture and engineering patterns. Dive into the adventures of Rick, Morty, and the countless characters they encounter across the multiverse, all from the palm of your hand.

[![Android CI](https://github.com/jamesgoodwin/rick-and-morty-universe/actions/workflows/android.yml/badge.svg)](https://github.com/jamesgoodwin/rick-and-morty-universe/actions/workflows/android.yml)

![Rick And Morty Image](/readme_logo.webp)


## Features
- **Character Guide**: Get detailed information about every character.
- **Episode Explorer**: Browse through episodes and their summaries.
- **Location Insights**: Discover the various planets and dimensions.

## Performance Highlights

The application is designed with a focus on:
- **Readability**: Clean codebase with extensive documentation making it easy to understand and navigate.
- **Maintainability**: Clean architecture and adherence to Android development best practices to ensure easy updates and feature additions.
- **Testability**: Unit and UI tests covering critical functionalities, ensuring robustness and reliability
  - Code decoupled from concrete implementation to make code more testable (using Hilt)
  - Stateless composable UI components for easier testability and compose previews
- **Scalability**: Efficient use of resources and optimized data handling to support a growing number of users and data volume.
  - HTTP caching to prevent overfetching of data 
- **Simplicity**: User-friendly interface, ensuring a seamless and intuitive user experience.
  - Reuse of Material design UI components
  - Reuse of Android standard libraries 

## Getting Started

Clone this repository and import into **Android Studio**

```bash
git clone https://github.com/jamesgoodwin/rick-and-morty-universe.git
