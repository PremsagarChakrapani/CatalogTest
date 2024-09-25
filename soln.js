const fs = require('fs');

// Function to read JSON from a file
function readJsonFile(filePath) {
    const jsonData = fs.readFileSync(filePath);
    return JSON.parse(jsonData);
}

// Function to decode the value based on the base
function decodeValue(baseStr, valueStr) {
    const base = parseInt(baseStr);
    return parseInt(valueStr, base);
}

// Function to calculate the constant term 'c' using Lagrange interpolation
function calculateConstantTerm(points, k) {
    let c = 0.0;

    // Lagrange interpolation formula for c
    for (let i = 0; i < k; i++) {
        let term = points[i].y;
        for (let j = 0; j < k; j++) {
            if (i !== j) {
                term *= (0 - points[j].x) / (points[i].x - points[j].x);
            }
        }
        c += term;
    }

    return c;
}

// Main function
function main() {
    const filePath = 'input.json'; // Path to your JSON input file
    const jsonInput = readJsonFile(filePath);

    // Extract n and k values
    const n = jsonInput.keys.n;
    const k = jsonInput.keys.k;

    // List to store points
    const points = [];

    // Iterate through the JSON to decode values
    for (let i = 1; i <= n; i++) {
        const pointData = jsonInput[i];
        const x = i; // Using the keys as x values
        const y = decodeValue(pointData.base, pointData.value);
        points.push({ x, y });
    }

    // Check if we have enough points to solve for the polynomial
    if (points.length < k) {
        console.log("Insufficient points to solve the polynomial.");
        return;
    }

    // Calculate the constant term 'c'
    const c = calculateConstantTerm(points, k);
    console.log("The constant term (secret 'c') of the polynomial is:", c);
}

main();
