            $("#cpfcnpj").keydown(function(){
                try {
                    $("#cpfcnpj").unmask();
                } catch (e) {}

                var tamanho = $("#cpfcnpj").val().length;

                if(tamanho < 11){
                    $("#cpfcnpj").mask("999.999.999-99");
                } else {
                    $("#cpfcnpj").mask("99.999.999/9999-99");
                }
            });

 $("#telefone").mask("(99) 9999?9-9999");

            $("#telefone").on("blur", function() {
                var last = $(this).val().substr( $(this).val().indexOf("-") + 1 );

                if( last.length == 3 ) {
                    var move = $(this).val().substr( $(this).val().indexOf("-") - 1, 1 );
                    var lastfour = move + last;
                    var first = $(this).val().substr( 0, 9 );

                    $(this).val( first + '-' + lastfour );
                }
            });
