
$('#toogle-menu-movil').click(function () {

    $('.contenedor-menu-movil').addClass('show');

});

$('#close-menu-movil').click(function () {

    $('.contenedor-menu-movil').removeClass('show');

})

$('.pestanas .tab').click(function () {

    $('.pestanas .tab').removeClass('active');

    $(this).addClass('active');

    let data = $(this).attr("data");

    $('.fast-search .cuerpo .buscador-hoteles').removeClass("active");

    $('.fast-search .cuerpo .buscador-paquetes').removeClass("active");

    $(data).addClass("active");

});

$('.busqueda-rapida-btn').click(function () {

    let valor = $(this).attr('tipo');

    if (valor=='hoteles') {

        let destino = $('#destinos-for-hoteles option:selected').val();
        location.href=url_web+"busqueda/"+valor+"/"+destino;

    } else{

        let destino = $('#destinos-for-paquetes option:selected').val();

        location.href=url_web+"busqueda/"+valor+"/"+destino;

    }

})

function gosearch(data) {

    location.href = url_web+"busqueda/paquetes/"+data;

}


$('#range').change(function () {

    reloadDataBusqueda();

})

function reloadDataBusqueda() {

    $('.cont-paquetes-resumen').append('<div class="loader-animate"><i class="fas fa-sync fa-spin"></i></div>');

    let loader = $('.loader-animate');

    setTimeout(() => {

        loader.addClass('animate');

    }, 1);



    let destino = $('.lista-destinos li.active a').attr("distrito");

    let rango = $('#range').val();

    let action = $('#action').val();

    $.ajax({

        data : {seccion_ajax : 'reload_busqueda', action: action, destino: destino, rango:rango},

        url  : url_web+'ajax_soap.php',

        type : "POST",

        dataType: 'JSON'

    }).done(function (respuesta) {

        $('.cont-paquetes-resumen .row-scroll').html(respuesta.html);

        $('.cont-paquetes-resumen .titulo').html(respuesta.titulo);

        loader.removeClass('animate');

        setTimeout(() => {

            loader.remove();

        }, 500);

    });



}



function imagenportada() {

    $.ajax().done(function (respuesta) {

        setTimeout(() => {

            $('#btn-ingresar-modal').html('Ingresar');

        }, 100);



    })

}
/*mis compras*/

$('#gomiscompras').click(function () {

//     location.href = url_web+"compras";

});


$('.owl-banner').owlCarousel({
    items:1,
    loop:true,
    margin:0,
    autoplay:true,
    autoplayTimeout:7000,
    autoplayHoverPause:true,
    animateOut: 'fadeOut'
});
