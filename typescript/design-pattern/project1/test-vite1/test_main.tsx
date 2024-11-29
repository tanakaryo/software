// Promiseの中身
console.log(new Promise(() => {}));

// 成功の場合
console.log(new Promise((resolve, reject) => resolve("Success.")));
// 失敗の場合
console.log(new Promise((resolve, reject) => reject("Failed.")));

// 成功の場合
console.log(new Promise((resolve, reject) => resolve("Success.")).then((text) => console.log( text + "I'm happy.")));
// 失敗の場合
console.log(new Promise((resolve, reject) => reject("Failed.")).catch((text) => console.log( text + "I'm bored.")));