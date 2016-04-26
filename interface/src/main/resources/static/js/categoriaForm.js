categoriaSelecionada;
blockAtual;
function subCategoria(i) {


            nextBlock = i+1;
            var url = '/admin/menu/subCategoria?passoAtual='+i+'&nextBlock='+ nextBlock;
            var block = "block"+i;

            if(i==1){
                form = document.forms.categoriaPricipal

                    for (x=0;x<form.catPrincipal.length;x++){
                            if (form.catPrincipal[x].selected){
                             //alert(InvForm.SelBranch[x].value);
                             catPaiId = form.catPrincipal[x].value;
                             categoriaSelecionada = catPaiId;
                            }
                         }
                url = url+'&idCategoriaPai='+catPaiId;
                $("#resultBlock2").load(url);
                limparPainesDeCategoria(i);
            }else{
                form = document.forms.namedItem(block);
//                form = document.forms.block2 form.elements[0].name

                if(form!=null){
                    for(z=0;z<form.elements.length;z++){
                        if(form.elements[z].name==block){
                            element = form.elements[z];

                            for(x =0;x<element.length;x++){
                                if(element.options[x].selected){
                                   catPaiId = element.options[x].value;
                                }
                            }

                        }
                    }

                }else{
                element = document.getElementsByName(block);
                selectItem = element.item(0);
                for(x =0;x<selectItem.length;x++){
                        if(selectItem.options[x].selected){
                           catPaiId = selectItem.options[x].value;
                           categoriaSelecionada = catPaiId;
                        }
                    }
                }


                url = url+'&idCategoriaPai='+catPaiId;
                $("#resultBlock"+(i+1)).load(url);
                $("#button"+i).html("");
                limparPainesDeCategoria(i);
                console.log("categoria selecionada: " + categoriaSelecionada);
                blockAtual = "block"+(i+1);
//                temProximo("block"+(i+1));

            }



        };

function enviarFomularioDeProduto(){
               try{
               element = document.getElementsByName(blockAtual);
                               selectItem = element.item(0);

                               if(selectItem==null){
                               document.getElementById("categoriaProduto").value=categoriaSelecionada;
                               document.getElementById("categoriaPricipal").submit();
                                   console.log("tem mais categorias para escolher")
                               }else{
                                  alert("Selecione a categoria final");
                               }

               }catch(err){
               alert("Erro inesperado")
               }



}

function logarCategoriaSelecionada(){

                console.log("categoria selecionada: " + categoriaSelecionada);
}

function limparPainesDeCategoria(i){
               var todosSelects = ["#resultBlock1","#resultBlock2","#resultBlock3","#resultBlock4","#resultBlock5","#resultBlock6","#resultBlock7","#resultBlock8","#resultBlock9","#resultBlock10"];
               x =i+2;
               for(;x<=todosSelects.length;x++){
                   $("#resultBlock"+x).html("");
                   }

         }

function cadastrarCategoriaAtrelada(i){

        var form;
        var catPaiId = "";
        var x = 0;
        var categoriaNova;
        var url="/admin/cadastrarCategoriasFilhas?catPaiId="
        var cat ="cat"+i;
        var block = "block";

         if(i==2){
            categoriaNova = document.getElementById(cat).value;
            form = document.forms.categoriaPricipal

            for (x=0;x<form.catPrincipal.length;x++){
                    if (form.catPrincipal[x].selected){
                     //alert(InvForm.SelBranch[x].value);
                     catPaiId = form.catPrincipal[x].value;
                    }
                 }
//                 $("#resultBlock"+i).load(url);
//                 alert(catPaiId+' '+  categoriaNova);

        }else {
                        categoriaNova = document.getElementById(cat).value;
//                        form = document.forms.block3
                        block = block+(i-1);
                        form = document.forms.namedItem(block);

                        for(z=0;z<form.elements.length;z++){
                            if(form.elements[z].name==block){
                                element = form.elements[z];

                                for(x =0;x<element.length;x++){
                                    if(element.options[x].selected){
                                       catPaiId = element.options[x].value;
                                    }
                                }
                            }
                        }
                 }
                   categoriaNova = categoriaNova.split(' ').join('%20');
                  url = url + catPaiId + '&categoriaNova=' + categoriaNova + '&blockAtual=' + i;
                 $("#resultBlock"+i).load(url)
 }



var specialKeys = new Array();
        specialKeys.push(8); //Backspace
        specialKeys.push(9); //Tab
        specialKeys.push(46); //Delete
        specialKeys.push(36); //Home
        specialKeys.push(35); //End
        specialKeys.push(37); //Left
        specialKeys.push(39); //Right

        function IsAlphaNumeric(e,i) {
            var keyCode = e.keyCode == 0 ? e.charCode : e.keyCode;
            var ret = ((keyCode >= 48 && keyCode <= 57) || (keyCode >= 65 && keyCode <= 90) || (keyCode >= 97 && keyCode <= 122) || (specialKeys.indexOf(e.keyCode) != -1 && e.charCode != e.keyCode));
            document.getElementById("error"+i).style.display = ret ? "none" : "inline";
            return ret;
        }

