$("#inputBookTags").tagsinput({
    tagClass: "h4 badge badge-primary mb-0",
    trimValue: true,
    maxChars: 24,
    maxTags: 32,
    cancelConfirmKeysOnEmpty: false
});

$("#inputVideoTags").tagsinput({
    tagClass: "h4 badge badge-primary mb-0",
    trimValue: true,
    maxChars: 24,
    maxTags: 32,
    cancelConfirmKeysOnEmpty: false
});

$("#inputPodcastTags").tagsinput({
    tagClass: "h4 badge badge-primary mb-0",
    trimValue: true,
    maxChars: 24,
    maxTags: 32,
    cancelConfirmKeysOnEmpty: false
});

$("#inputBlogpostTags").tagsinput({
    tagClass: "h4 badge badge-primary mb-0",
    trimValue: true,
    maxChars: 24,
    maxTags: 32,
    cancelConfirmKeysOnEmpty: false
});


$("#submitBtn").click(function () {
    $("#create"+$("#pills-tab-category").find(".active").text()+ "Form").submit();
});