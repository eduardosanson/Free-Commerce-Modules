function enviarFormBusca(){

    document.getElementById("pagina").value=1;
     valorPesquisado = document.getElementById("buscarProdutoInput").value;


    if(valorPesquisado.length>3){

    document.getElementById("buscarProdutoForm").submit();
    }
}

function enviarFormBuscaCompleta(){

    document.getElementById("orderBy").value=document.getElementById("order").value;
    document.getElementById("novo").value=document.getElementById("estadoProduto").value;
    document.getElementById("page").value=1;

    document.getElementById("buscarProdutoForm").submit();

}


function enviarFormBuscaCompletaGrid(){

    document.getElementById("orderBy").value=document.getElementById("order").value;
    document.getElementById("novo").value=document.getElementById("estadoProduto").value;
    document.getElementById("page").value=1;

    document.getElementById("buscarProdutoFormGrid").submit();

}

function enviarFormBuscaPorIndice(i){

    document.getElementById("orderBy").value=document.getElementById("order").value;
    document.getElementById("novo").value=document.getElementById("estadoProduto").value;
    document.getElementById("page").value=i;

    document.getElementById("buscarProdutoForm").submit();

}

function enviarFormBuscaPorIndiceGrid(i){

    document.getElementById("orderBy").value=document.getElementById("order").value;
    document.getElementById("novo").value=document.getElementById("estadoProduto").value;
    document.getElementById("page").value=i;

    document.getElementById("buscarProdutoFormGrid").submit();
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
     var url = 'produto/' + page + '?nomeProduto';

     var produtoPesquisado = document.getElementById("produtoPesquisado").innerText;

     var categoria = document.getElementById("categoriaSelecionada").value;

     if(categoria!=""){
        url = 'produto/' + page + '?categoria=' + categoria;
     }else {
         url = url + produtoPesquisado;
     }


    $("#produtoList").load(url);

}

function buscarPorCategoria(id){

    url = "/produto?categoria=";

    categoria = document.getElementById(id).innerText;
    url = url + categoria + "&pagina=1";

    window.location=url;
}