function init() {

    commonFunc.loadModule($('.main-header'), FP_MainHeader, null);
    commonFunc.loadModule($('.main-content'), FP_SignContainer, null);
    commonFunc.loadModule($('.main-footer'), FP_MainFooter, null);

    $('#modalWindow').on({
        'hidden.bs.modal': function () {
            $('#modalWindow .modal-dialog').attr('class', 'modal-dialog modal-dialog-centered');
            $('#modalWindow .modal-body').empty();
            $('#modalWindow .modal-title').empty();
            $('#modalWindow .btn-primary').empty();
            $('#modalWindow .btn-secondary').empty();
            $('#modalWindow .btn-primary').attr('data-dismiss', 'modal');
            $('#modalWindow .btn-secondary').attr('data-dismiss', 'modal');

        }
    })
}



