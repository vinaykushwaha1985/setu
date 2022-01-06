window.addEventListener('resize', function(){
    addRequiredClass();
})


function addRequiredClass() {
    if (window.innerWidth < 1024) {
        document.body.classList.add('mobile')
    } else {
        document.body.classList.remove('mobile') 
    }
}

window.onload = addRequiredClass

let hamburger = document.querySelector('.hamburger')
let mobileNav = document.querySelector('.nav-list')
 
let bars = document.querySelectorAll('.hamburger span')

let isActive = false

hamburger.addEventListener('click', function() {
    mobileNav.classList.toggle('open')
    if(!isActive) {
        bars[0].style.transform = 'rotate(45deg)'
        bars[1].style.opacity = '0'
        bars[2].style.transform = 'rotate(-45deg)'
        isActive = true
    } else {
        bars[0].style.transform = 'rotate(0deg)'
        bars[1].style.opacity = '1'
        bars[2].style.transform = 'rotate(0deg)'
        isActive = false
    }
    

})



$(document).ready(function() {
    //toggle the component with class accordion_body
    $(".accordion_head").click(function() {
      if ($('.accordion_body').is(':visible')) {
        $(".accordion_body").slideUp(300);
        $(".plusminus").text('+');
      }
      if ($(this).next(".accordion_body").is(':visible')) {
        $(this).next(".accordion_body").slideUp(300);
        $(this).children(".plusminus").text('+');
      } else {
        $(this).next(".accordion_body").slideDown(300);
        $(this).children(".plusminus").text('-');
      }
    });
  });

  
  // $('.fade').slick({
  //   dots: false,
  //   arrows: false,
  //   autoplay: true,
  //   speed: 700,
  //   fade: true
  // });

  $(document).ready(function(){
    $("#signin-btn").click(function(){
      $("#signin-verify-wrap").remove();
    });  
  });

 