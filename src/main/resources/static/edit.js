
$(document).ready(function () {

    $('.table .editBtn').on('click', function (event) {
        event.preventDefault();
        let href = $(this).attr('href')


         $.get(href, function (user) {
           $('.myForm #id').val(user.id);
           $('.myForm #username').val(user.username);
           $('.myForm #lastName').val(user.lastName);
           $('.myForm #age').val(user.age);
           $('.myForm #email').val(user.email);
           $('.myForm #password').val(user.password);
    })

        $('#editModal').modal();

    });
});
