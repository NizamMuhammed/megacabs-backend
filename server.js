const express = require("express");
const cors = require("cors");

const app = express();

// Enable CORS for requests from http://localhost:3000 (React frontend)
app.use(
  cors({
    origin: "http://localhost:3000", // Allow frontend to connect
    methods: ["GET", "POST", "OPTIONS"], // Allow specific HTTP methods
    allowedHeaders: ["Content-Type", "Authorization"], // Allow specific headers
  })
);

// Example API route
app.get("/api/v1/cabs", (req, res) => {
  res.json({ message: "Cabs data" });
});

// Start the server
app.listen(8080, () => {
  console.log("Server is running on http://localhost:8080");
});
