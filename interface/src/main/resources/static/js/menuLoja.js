 progressBarr = '<div id="myModal" class="modal fade">' +
                     '<div class="modal-dialog">' +
                         '<div class="modal-content">' +
                             '<div class="modal-header">' +
                                 '<h4 class="modal-title">Por Favor Aguarde!</h4>' +
                             '</div>' +
                             '<div class="modal-body">' +
                                '<div class="progress progress-striped active" style="margin-bottom:0;"><div class="progress-bar" style="width: 100%"></div></div>'+
                             '</div></div></div></div>' +
                             '<script type="text/javascript">' +
                              	'$(document).ready(function(){' +
                              		'$("#myModal").modal(\'show\');' +
                              	'});' +
                              '</script>'


 function createProduct() {
    var url = '/store/menu/createProduct';


    $("#resultsBlock").html(progressBarr);
    $("#resultsBlock").load(url);
}

function showProduct() {
    var url = '/store/menu/showMyProducts/1';

    $("#resultsBlock").html(progressBarr);
    $("#resultsBlock").load(url);

}
function showChangePassword() {
    var url = '/store/menu/changePassword';

    $("#resultsBlock").load(url);
}
function showChangeAddress() {
    var url = '/store/menu/changeAddress';

    $("#resultsBlock").load(url);
}
function showChangeRegistration() {
    var url = '/store/menu/changeRegistration';

    $("#resultsBlock").load(url);
}

function showProductPage(page) {
    var url = '/store/menu/showMyProductsPage/';

    $("#resultProduct").load(url+page);
}
