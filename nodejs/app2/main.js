const fs = require('fs');
let text = fs.readFileSync('test.txt', 'utf-8');
console.log(text);