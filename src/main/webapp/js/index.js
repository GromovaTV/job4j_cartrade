$(document).ready(function () {
    $.ajax({
        cache: false,
        type: 'GET',
        url: '/cartrade/all.do',
        dataType: 'json'
    }).done(function (data) {
        show_ads(data);
    }).fail(function () {
    });
});

$(document).ready(function () {
    $.ajax({
        cache: false,
        type: 'GET',
        url: '/cartrade/auth.do',
        dataType: 'json'
    }).done(function (data) {
        if (data) {
            var li = $('<li class="nav-item">\n' +
                '<button type="submit" class="btn btn-primary mr-3" onclick="return redirect();">Разместить объявление</button></li>' +
                '<li class="nav-item">\n' +
                '<button class="btn btn-secondary" type="button" onclick="logout();">' +
                data.name + " | Выйти" + '</button></li>');
        } else {
            var li = $('<li class="nav-item">\n' +
                '<a class="nav-link" href="/cartrade/login.html">Войти</a>\n' +
                '</li>' +
                '<li class="nav-item">\n' +
                '<a class="nav-link" href="/cartrade/reg.html">Регистрация</a>\n' +
                '</li>');
        }
        $('.nav').append(li);
    }).fail(function () {
    });
});

function ads_filter() {
    var selectedBrands = $("#brands").val();
    var selectedBodies = $("#bodies").val();
    var priceRange = priceSlider.noUiSlider.get();
    console.log("Выбранные марки:", selectedBrands);
    console.log("Выбранные типы кузовов:", selectedBodies);
    console.log("Диапазон цен:", priceRange);
    $.ajax({
        cache: false,
        type: 'POST',
        url: '/cartrade/searchfilter',
        contentType: 'application/json; charset=UTF-8',
        data: JSON.stringify({
            brands: selectedBrands,
            bodies: selectedBodies,
            min: priceRange[0],
            max:  priceRange[1]
        }),
        dataType: 'json'
    }).done(function (data) {
        $('#table tbody').remove();
        show_ads(data);
    }).fail(function () {
        console.log("Error");
    });
}

function show_ads(data) {
    var ads = data.ads; // Получаем список реклам
    var user = data.user; // Получаем объект пользователя
    var tbody = $('<tbody></tbody>');
    for (const item of ads) {
        var status = "Продается";
        if (item.sold) {
            status = "Продано";
        }
        let fileName = "placeholder.png";
        if (item.photos.length > 0) {
            const photo = item.photos[0];
            const nameParts = photo.path.split("\\");
            fileName = nameParts[2];
            console.log(fileName);
        }
        var td = $('<td>' + item.user.name + " " + '</td>');
        if (user != null && user.name === item.user.name) {
            console.log(item.id);
            td.append('<a href="/cartrade/edit.html" onclick="return selectItem(' + item.id + ');"><i class="fa fa-edit mr-3"></i></a>\n');
        }
        var tr = $(
            '<tr>' +
            '<td><img src="/cartrade/download.do?name=' + fileName + '" width="100px" height="100px"/></td>' +
            '<td><a href="/cartrade/about.html" data-id="' + item.id
            + '" class="link" ' +
            'onclick="return selectItem(' + item.id + ');"' +
            '>'
            + item.car.brand.name + '</a></td>' +
            '<td>' + item.car.body.name + '</td>' +
            '<td>' + item.price + "р." + '</td>' +
            '</tr>');
        tr.prepend(td);
        tbody.append(tr);
    }
    $('#table').append(tbody);
}