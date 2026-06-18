async function correctText()
{
    document.getElementById("corrected").innerText="Correcting...";
    document.getElementById("explanation").innerText="";
    const text=document.getElementById("inputText").value;
    const response=await fetch("/correct",{
        method:"POST",
        headers: {
            "Content-Type":"application/json"
        },
        body: JSON.stringify({text: text})
    });
    const data=await response.json();
    document.getElementById("corrected").innerText=data.corrected;
    document.getElementById("explanation").innerText=data.explanation;
}