 /**
 * This demo was prepared for you by Petr Tichy - Ihatetomatoes.net
 * Want to see more similar demos and tutorials?
 * Help by spreading the word about Ihatetomatoes blog.
 * Facebook - https://www.facebook.com/ihatetomatoesblog
 * Twitter - https://twitter.com/ihatetomatoes
 * Google+ - https://plus.google.com/u/0/109859280204979591787/about
 * Article URL: http://ihatetomatoes.net/simple-parallax-scrolling-tutorial/
 */

( function( $ ) {
	
	// Setup variables
	$window = $(window);
	$slide = $('.homeSlide');
	$body = $('body');

	//Hacemos que los botones de la barra de navegación se resalten cuando pasamos sobre ellos y cuando hacemos click.
	$('.elemento-nav').bind({

		 click: function() {
			$(this).addClass( 'active' );
			},
		mouseenter: function() {
			$(this).addClass( 'inside' );
			
			},
		mouseleave: function() {
			$(this).removeClass( 'inside' );
			
			}
	});

	//Con esta funcion hacemos que el logo de Spark se mueva hacia la izquierda con el primer scroll.
				$(function () {
					$('.logo').hide();
					$('.logo').fadeIn(300);

				  var $pos0 = 70;
				  
				  $window.scroll(function () {
				     if ($window.scrollTop() <= $pos0){
				        $('.logo').removeClass('mov');
				        
				        
				    }
				     else{
				        $('.logo').addClass('mov');
				  
				    	

				     }
				   });
				});



	//Con esta función hacemos que el logo de la barra de navegación aparezca y que se oculte el logo grande.

			$(function () {
				  $('.logopeque').hide();
				  $('#header').hide();
				  var $pos = 140;
				  $window.scroll(function () {
				     if ($window.scrollTop() <= $pos){
				        //$('.logo').removeClass('mov');
				        $('.logo').fadeIn();
				    	$('.logopeque').fadeOut();
				    	$('#header').fadeOut();
				    }
				     else {
				     	$('.logopeque').fadeIn();
				        $('.logo').fadeOut();
				    	$('#header').fadeIn();
				     }
				   });
				});
		
		//Con esta funcion hacemos que cuando pasamos de slide se muestren o no los 
		//diferentes cuadros que poseen codigo o instrucciónes.		
			$(function () {
				  
				  $('.code-text').hide();
				  var $pos2 = 400;
				  var $pos3 = 900;
				  var $pos4 = 1200;
				  var $pos5 = 1600;
				  $window.scroll(function () {
				     if (($window.scrollTop()>$pos2 && $window.scrollTop()<$pos3)||($window.scrollTop()>$pos4 && $window.scrollTop()<$pos5))
				      	$('.code-text').fadeIn();
				     else {
				      	$('.code-text').fadeOut();
				     }
				   });
				});

    //FadeIn all sections   
	$body.imagesLoaded( function() {
		setTimeout(function() {
		      
		      // Resize sections
		      adjustWindow();
		      
		      // Fade in sections
			  $body.removeClass('loading').addClass('loaded');
			  
		}, 800);
	});


	function adjustWindow(){
		
		// Init Skrollr
		
		
		// Get window size
	    winH = $window.height();
	    
	    // Keep minimum height 600
	    if(winH <= 700) {
			winH = 700;
		} 
	    
	    // Resize our slides
	    $slide.height(winH);
	    
	    // Refresh Skrollr after resizing our sections
	    
	    
	}
		
} )( jQuery );