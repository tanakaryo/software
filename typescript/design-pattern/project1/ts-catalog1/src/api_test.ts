async function func() {
    const textResponse = await fetch("https://pokeapi.co/api/v2/pokemon?limit=1");
    const textData = await textResponse.text();
    console.log(textData)
}

func();