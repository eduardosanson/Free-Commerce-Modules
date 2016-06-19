function addAoCarrinho(produtoId,quantidade){
var url = "/produto/addAoCarrrinho?produtoId="+produtoId + "&quantidade="+quantidade;

    loadCarrinhoHeader(url);

    return;

}

function carrinhoHeaderPopulate(){
    var url = "/produto/carrinhoHeaderPopulate";
    $("#carrinho").load(url);
}


function loadCarrinhoHeader(url){
    $("#carrinho").load(url);
//    $("#carrinho").load(url, {limit: 25},
//                       function (responseText, textStatus, req) {
//                           if (textStatus == "error") {
//                             return "oh noes!!!!";
//                           });
}

function CarrinhoMenos(produtoId){


    elementId = "value"+produtoId;
    reduzir = "/carrinho/subtrair/"+produtoId;


    document.getElementById(elementId).value--

    if(document.getElementById(elementId).value==0){
        destroiProduto(produtoId);

    }else{
        loadCarrinhoHeader(reduzir);
        acertarCarrinho(produtoId);
    }

}

function removerDoCarrinho(i){

url = "/carrinho/remover/"+i;

$("#carrinho").load(url);

//var qtd = document.getElementById("qtd"+i).innerHTML;
//var totalCarrinho = document.getElementById("totalCarrinho").innerHTML;
//total = totalCarrinho-qtd;
//document.getElementById("totalCarrinho").innerHTML=total;
//document.getElementById(i).remove();

}

function destroiProduto(produtoId){

    produto = "produto"+produtoId
    destroiUrl="/carrinho/remover/"+produtoId;

    loadCarrinhoHeader(destroiUrl);
    subtrairValorTotal(produtoId);
    document.getElementById(produto).innerHTML="";

    var row = document.getElementById(produto);
        var table = row.parentNode;
        while ( table && table.tagName != 'TABLE' )
            table = table.parentNode;
        if ( !table )
            return;
        table.deleteRow(row.rowIndex);

        if(document.getElementById("tabelaCarrinho").rows.length<2){
                 deletarButtonFinalizarCompra()
        }

}

function deletarButtonFinalizarCompra(){
    var elem = document.getElementById('urlFinalizarCompra');
        elem.parentNode.removeChild(elem);
}


function subtrairValorTotal(produtoId){

    elementId = "valorProduto"+produtoId

    value = document.getElementById(elementId).innerHTML
    valorProduto = peneiraDeLetras(value);
    value = document.getElementById("valorTotal").innerHTML
    valorTotal = peneiraDeLetras(value);;
    document.getElementById("valorTotal").innerHTML = converterParaReal(valorTotal - valorProduto);
}

function subtrairValorTotalPorProduto(produtoId){

    elementId = "valorProduto"+produtoId;
    valorTotalPorProduto = "valorTotalPorProduto" +produtoId;

    value = document.getElementById(elementId).innerHTML
    valorUnitario = peneiraDeLetras(value);
    value = document.getElementById(valorTotalPorProduto).innerHTML
    valorTotal = peneiraDeLetras(value);;
    document.getElementById(valorTotalPorProduto).innerHTML = converterParaReal(valorTotal - valorUnitario);
}

function somarValorTotalPorProduto(produtoId,value){

    elementId = "valorProduto"+produtoId;
    valorTotalPorProduto = "valorTotalPorProduto" +produtoId;


    document.getElementById(valorTotalPorProduto).innerHTML = converterParaReal(value);
}

function peneiraDeLetras(value){
    v = value.replace("R$","");
    return parseFloat(v);

}

function somarValorTotal(produtoId,value){


    document.getElementById("valorTotal").innerHTML = converterParaReal(value);
}

function CarrinhoMais(produtoId){
    elementId = "value"+produtoId;

    somar = "/carrinho/somar/"+produtoId;

    loadCarrinhoHeader(somar);



    acertarCarrinho(produtoId)


}

function converterParaReal(value){
    return "R$ " + parseFloat(value).toFixed(2);
}

function acertarCarrinho(produtoId){

    var xmlhttp = new XMLHttpRequest();
    var url = "/carrinho/produto/"+produtoId+".json";

    xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            var myArr = JSON.parse(xmlhttp.responseText);
            my(myArr);
        }
    };
    xmlhttp.open("GET", url, true);
    xmlhttp.send();

    function my(arr) {

            document.getElementById("value"+produtoId).value=arr.quantideDoProduto

            somarValorTotalPorProduto(produtoId,arr.valorPorProduto)

            somarValorTotal(produtoId,arr.valorCarrinho);

}

}

