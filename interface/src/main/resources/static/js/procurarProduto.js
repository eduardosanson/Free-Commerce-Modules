function enviarFormBusca(){

    document.getElementById("pagina").value=1;
     valorPesquisado = document.getElementById("buscarProdutoInput").value;


    if(valorPesquisado.length>3){

    document.getElementById("buscarProdutoForm").submit();
    }


}

function isValidForm(){


    if(valorPesquisado.length<3){
        alert("utilize pelo menos três letras para efetua a busca")
        return false;
        }else{
        return true;
        }

}

function productPage(page){
// {pageNumber}/{nomeProduto}';
 var url = 'produto/' + page + '/';

 var produtoPesquisado = document.getElementById("produtoPesquisado").innerText;

 url = url + produtoPesquisado;

    $("#produtoList").load(url);

}