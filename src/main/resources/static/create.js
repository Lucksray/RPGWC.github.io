let name = document.getElementById("name");
let material = document.getElementById("material");
let materialAmount = document.getElementById("materialAmount");
let creator = document.getElementById("creator");
let origin = document.getElementById("origin");
let classType = document.getElementById("classType");
let subClassType = document.getElementById("subClassType")

const form = document.getElementById("weapon--form");
form.addEventListener('submit',submitWeapon);

function submitWeapon(event){
    event.preventDefault();
    let object = {name:name.value,material:material.value,materialAmount:materialAmount.value,
        creator:creator.value,origin:origin.value,classType:classType.value,subClassType:subClassType.value};
    console.log(object);
    alert("Submitted");

    fetch(`/create`,{
        method:"POST",
        body:JSON.stringify(object),
        headers:{
            "Content-Type":"application/json"
        }
    
    }).then(res => {    
    
        res.json().then(body => {
            document.getElementsByClassName("main--element").innerHTML = JSON.stringify(body)
        })    
    
    }).catch(err =>{
        console.log(err)
        document.getElementsByClassName("main--element").innerHTML= `<p>Error: ${err.message}</p>`
    });
}

function getLatest(){
    fetch("/getLatest").then(res =>{res.json().then(body => {
        document.getElementsByClassName("main--element").innerHTML = JSON.stringify(body)
        })
    }).catch(err =>{
        console.log(err);
    });
}