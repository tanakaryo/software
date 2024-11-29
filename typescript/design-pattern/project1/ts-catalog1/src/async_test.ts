import { resolve } from "bun";

function request(): Promise<string> {
    return new Promise((resolve) => {
        setTimeout(() => {
            resolve("hello");
        }, 1000)
    });
}

async function main() {
    const result = await request();
    console.log(result);
}

main();