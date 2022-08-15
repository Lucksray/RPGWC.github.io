const stat = document.getElementById("statTable");
const origin = document.getElementById("originTable");
const clas = document.getElementById("classTable");
const replace = document.getElementById("body--element");

stat.addEventListener('callTableStat', callTableStat);
origin.addEventListener(onclick="callTableOrigin", callTableOrigin);
clas.addEventListener(onclick="callTableClass", callTableClass);

function callTableStat(){
    const response = fetch(`/stat/getAll`).then(res =>{
        res.json().then(body => {
            replace.innerHTML = JSON.stringify(body)
        })
    }).catch(err =>{
        console.log(err);
        replace.innerHTML=`<p>Error: ${err.message}</p>`;
    });
}

function callTableOrigin(){
    fetch(`/origin/getAll`).then(res =>{
        res.json().then(body => {
            replace.innerHTML = JSON.stringify(body)
        })
    }).catch(err =>{
        console.log(err);
        replace.innerHTML=`<p>Error: ${err.message}</p>`;
    });
}

function callTableClass(){
    fetch(`/class/getAll`).then(res =>{
        res.json().then(body => {
            replace.innerHTML = JSON.stringify(body)
        })
    }).catch(err =>{
        console.log(err);
        replace.innerHTML=`<p>Error: ${err.message}</p>`;
    });
}