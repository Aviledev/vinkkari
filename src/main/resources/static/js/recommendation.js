$('#inputBookTags').tagsinput({
    tagClass: 'h4 badge badge-primary mb-0',
    trimValue: true,
    maxChars: 24,
    maxTags: 32,
    cancelConfirmKeysOnEmpty: false
});


$('#submitBtn').click(function () {
    $('#create'+$('.nav-pills .active').text()+ 'Form').submit();
});