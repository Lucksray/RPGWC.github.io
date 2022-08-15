let id = document.getElementById("id");
let itemName = document.getElementById("name");

const form = document.getElementById("Submit");
form.addEventListener("submit", deleteWeapon);

function deleteWeapon(event){
    let item = {id:number.value,itemName:itemName.value};
    console.log(event);
    event.preventDefault();
    alert("deleting");

    fetch(`/delete?id=${item.id}&name=${item.itemName}`,{
        method:"DELETE",
        body:JSON.stringify(item),
        headers:{
            "Content-Type":"application/json"
        }
    }).then(res => {
        res.json().then(body => {
            document.body.innerHTML = JSON.stringify(body)
        })
    }).catch(err =>{
        console.log(err)
        document.body.innerHTML = `<p>Error: ${err.message}</p>`
    })
}