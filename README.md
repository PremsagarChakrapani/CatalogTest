Shamir's Secret Sharing: Matrix Method

Problem Statement
Given a polynomial of degree m, we require k = m + 1 points to reconstruct the polynomial coefficients, including the constant term c. The polynomial is represented as:

f(x) = a_m x^m + a_{m-1} x^{m-1} + ... + a_1 x + c

Input Format
The input is provided in JSON format, containing the number of roots and the roots themselves in various bases:

{
    "keys": {
        "n": 9,
        "k": 6
    },
    "1": {
        "base": "10",
        "value": "28735619723837"
    },
    ...
}
**Steps to Solve the Problem**
Read JSON Input: The input JSON file is read and parsed to extract the roots.

Decode Y Values: Each y value is encoded in a specified base and needs to be decoded into decimal form.

Construct the Matrix:

Create a matrix A where each row corresponds to the powers of x for each point.
Create a vector b containing the corresponding y values.
Solve the Matrix: Use Gaussian elimination or another suitable method to solve for the coefficients of the polynomial.

Extract Constant Term: The constant term c is extracted from the resulting coefficients.

Example
For an example input:

{
    "keys": {
        "n": 9,
        "k": 6
    },
    "1": {
        "base": "10",
        "value": "28735619723837"
    },
    "2": {
        "base": "16",
        "value": "1A228867F0CA"
    },
    ...
}
The output after processing and solving the polynomial would yield the constant term c.

**Output**
The program outputs the constant term c which is part of the original polynomial.

**Requirements**
Node.js
Any JSON parsing library (e.g., fs for reading files)

**How to Run**
Clone the repository.
Ensure Node.js is installed on your machine.
Run the script to process the JSON input file and output the constant term c.
node index.js
