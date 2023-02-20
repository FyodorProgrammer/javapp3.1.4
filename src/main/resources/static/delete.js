
$(document).ready(function () {
    $('.table .deleteBtn').on('click', function (event) {
        event.preventDefault();
        let href = $(this).attr('href')
        $.get(href, function (user) {
            $('.myDeleteForm #dId').val(user.id);
            $('.myDeleteForm #dUsername').val(user.username);
            $('.myDeleteForm #dLastName').val(user.lastName);
            $('.myDeleteForm #dAge').val(user.age);
            $('.myDeleteForm #dEmail').val(user.email);
        })


        $('.myDeleteForm #deleteModal').modal();

    });
})