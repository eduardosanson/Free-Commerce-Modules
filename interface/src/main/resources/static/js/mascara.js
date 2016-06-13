 function mascaraMutuario(o,f){
     v_obj=o
     v_fun=f
     setTimeout('execmascara()',1)
 }

 function execmascara(){
     v_obj.value=v_fun(v_obj.value)
 }

 function cpfCnpj(v){

     //Remove tudo o que não é dígito
     v=v.replace(/\D/g,"")

     if (v.length <= 13) { //CPF

         //Coloca um ponto entre o terceiro e o quarto dígitos
         v=v.replace(/(\d{3})(\d)/,"$1.$2")

         //Coloca um ponto entre o terceiro e o quarto dígitos
         //de novo (para o segundo bloco de números)
         v=v.replace(/(\d{3})(\d)/,"$1.$2")

         //Coloca um hífen entre o terceiro e o quarto dígitos
         v=v.replace(/(\d{3})(\d{1,2})$/,"$1-$2")

     } else { //CNPJ

         //Coloca ponto entre o segundo e o terceiro dígitos
         v=v.replace(/^(\d{2})(\d)/,"$1.$2")

         //Coloca ponto entre o quinto e o sexto dígitos
         v=v.replace(/^(\d{2})\.(\d{3})(\d)/,"$1.$2.$3")

         //Coloca uma barra entre o oitavo e o nono dígitos
         v=v.replace(/\.(\d{3})(\d)/,".$1/$2")

         //Coloca um hífen depois do bloco de quatro dígitos
         v=v.replace(/(\d{4})(\d)/,"$1-$2")

     }

     return v

 }

 function check(e,value)
 {
     //Check Charater
     var unicode=e.charCode? e.charCode : e.keyCode;
     if (value.indexOf(".") != -1)if( unicode == 46 )return false;
     if (unicode!=8)if((unicode<48||unicode>57)&&unicode!=46)return false;
 }
 function checkLength()
 {
     var fieldLength = document.getElementById('cpfcnpj').value.length;
     //Suppose u want 4 number of character
     if(fieldLength <= 17


     ){
         return true;
     }
     else
     {
         var str = document.getElementById('cpfcnpj').value;
         str = str.substring(0, str.length - 1);
         document.getElementById('cpfcnpj').value = str;
     }
 }
