function addAoCarrinho(produtoId,quantidade){
var url = "/produto/addAoCarrrinho?produtoId="+produtoId + "&quantidade="+quantidade;

$("#carrinho").load(url);
}

function CarrinhoMenos(produtoId){

    elementId = "value"+produtoId;

    document.getElementById(elementId).value--

    if(document.getElementById(elementId).value==0){
        destroiProduto(produtoId);

    }else{
        subtrairValorTotalPorProduto(produtoId)
        subtrairValorTotal(produtoId);
    }

}

function destroiProduto(produtoId){

    produto = "produto"+produtoId

    subtrairValorTotal(produtoId);
    document.getElementById(produto).innerHTML="";

}

function subtrairValorTotal(produtoId){

    elementId = "valorProduto"+produtoId

    value = document.getElementById(elementId).innerHTML
    valorProduto = parseFloat(value.split(" ")[1])
    value = document.getElementById("valorTotal").innerHTML
    valorTotal = parseFloat(value.split(" ")[1]);
    document.getElementById("valorTotal").innerHTML = converterParaReal(valorTotal - valorProduto);
}

function subtrairValorTotalPorProduto(produtoId){

    elementId = "valorProduto"+produtoId;
    valorTotalPorProduto = "valorTotalPorProduto" +produtoId;

    value = document.getElementById(elementId).innerHTML
    valorUnitario = parseFloat(value.split(" ")[1])
    value = document.getElementById(valorTotalPorProduto).innerHTML
    valorTotal = parseFloat(value.split(" ")[1]);
    document.getElementById(valorTotalPorProduto).innerHTML = converterParaReal(valorTotal - valorUnitario);
}

function somarValorTotalPorProduto(produtoId){

    elementId = "valorProduto"+produtoId;
    valorTotalPorProduto = "valorTotalPorProduto" +produtoId;

    value = document.getElementById(elementId).innerHTML
    valorUnitario = parseFloat(value.split(" ")[1])
    value = document.getElementById(valorTotalPorProduto).innerHTML
    valorTotal = parseFloat(value.split(" ")[1]);
    document.getElementById(valorTotalPorProduto).innerHTML = converterParaReal(valorTotal + valorUnitario);
}

function somarValorTotal(produtoId){

    elementId = "valorProduto"+produtoId
    value = document.getElementById(elementId).innerHTML
    valorProduto = parseFloat(value.split(" ")[1])
    value = document.getElementById("valorTotal").innerHTML
    valorTotal = parseFloat(value.split(" ")[1]);
    document.getElementById("valorTotal").innerHTML = converterParaReal(valorTotal + valorProduto);
}

function CarrinhoMais(produtoId){
    elementId = "value"+produtoId;

    document.getElementById(elementId).value++

    somarValorTotal(produtoId);

    somarValorTotalPorProduto(produtoId);
}

function converterParaReal(value){
    return "R$ " + value.toFixed(2);
}