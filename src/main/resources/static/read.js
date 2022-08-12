function callTableStat(){
    const response = fetch(`/stat/getAll`).then(res =>{
        res.json().then(body => {
            document.body.innerHTML = JSON.stringify(body)
        })
    }).catch(err =>{
        console.log(err);
        document.body.innerHTML=`<p>Error: ${err.message}</p>`;
    });

    const data = await response.json();


}

function callTableOrigin(){
    fetch(`/origin/getAll`).then(res =>{
        res.json().then(body => {
            document.body.innerHTML = JSON.stringify(body)
        })
    }).catch(err =>{
        console.log(err);
        document.body.innerHTML=`<p>Error: ${err.message}</p>`;
    });
}

function callTableClass(){
    fetch(`/class/getAll`).then(res =>{
        res.json().then(body => {
            document.body.innerHTML = JSON.stringify(body)
        })
    }).catch(err =>{
        console.log(err);
        document.body.innerHTML=`<p>Error: ${err.message}</p>`;
    });
}