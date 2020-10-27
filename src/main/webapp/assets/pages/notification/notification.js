 
    function notify(message, type, dl){
    	
        $.growl({
            message: message
        },{
            type: type,
            allow_dismiss: false,
            label: 'Cancel',
            className: 'btn-xs btn-inverse',
//            placement: {
//                from: 'top',
//                align: 'center'
//            },
            delay: (dl>0)? dl:2000,
//            animate: {
//                    enter: 'animated fadeInRight',
//                    exit: 'animated fadeOutRight'
//            },
            offset: {
                x: 20,
                y: 60
            }
        });
    };
    
     
