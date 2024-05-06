const fs = require('fs');
let text = "この内容をファイルに書き出します。";
fs.writeFileSync('testWrite.txt', text, (err) => {
    if (err) throw err;
})
console.log("ファイル書き込み完了.")