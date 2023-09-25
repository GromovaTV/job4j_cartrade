$(document).ready(function () {
    // Инициализация noUiSlider
    var priceSlider = document.getElementById('priceSlider');
    var minPrice = 100000;
    var maxPrice = 10000000;
    noUiSlider.create(priceSlider, {
        start: [minPrice, maxPrice],
        connect: true,
        step: 1000, // Шаг изменения
        range: {
            'min': minPrice,
            'max': maxPrice
        },
        format: {
            to: function (value) {
                return parseInt(value);
            },
            from: function (value) {
                return parseInt(value);
            }
        }
    });

    // Обработчик события изменения слайдера стоимости
    priceSlider.noUiSlider.on('update', function (values, handle) {
        $("#priceRange").text(values[0] + 'р - ' + values[1] + 'р');
    });

    $('.dropdown-item').click(function () {
        $(this).toggleClass('selected-option');
    });

    // Закрытие dropdown-menu при клике вне селекта
    $('.dropdown').on('click', function (e) {
        if ($(e.target).hasClass('dropdown-item')) {
            e.stopPropagation();
            $(e.target).toggleClass('selected');
        }
    });
});