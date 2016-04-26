function addAoCarrinho(produtoId,quantidade){
var url = "/produto/addAoCarrrinho?produtoId="+produtoId + "&quantidade="+quantidade;

$("#carrinho").load(url);
}