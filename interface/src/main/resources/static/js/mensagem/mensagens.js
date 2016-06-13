
function enviarPergunta(){

      document.getElementById("msgform").submit();

}

function exibirLogin(){
   mensagem = document.getElementById('mensagemId').value;

   if(mensagem==""){
        alert('Oops esqueceu de perguntar');
   } else{

   inserirDadosParaEnvioDeMensagem(mensagem);

     $("#loginModal").show();
   }



}

function fecharLoginModal(){

    $("#loginModal").hide()

}

function showQuickSingUpModal(){


    fecharLoginModal();

    $("#quickModal").show()
}

function closeQuickSingUpModal(){

    $("#quickModal").hide()

}

function inserirDadosParaEnvioDeMensagem(mensagem){
    produtos = document.getElementsByName('produtoId');
    mensagens = document.getElementsByName('mensagem');
    produtoId

    for(i=0;i<mensagens.length;i++){
        mensagens[i].value = mensagem;
    }

    for(i=0;i<produtos.length;i++){
           if(produtos[i].value!=""){
                produtoId = produtos[i].value;
                break;
           }
        }
    for(i=0;i<produtos.length;i++){
           if(produtos[i].value==""){
                produtos[i].value = produtoId;
           }
        }



}
