function debounce(func,timeout = 1000){
    let timer;
    return(...args)=>{
        clearTimeout(timer);
        timer = setTimeout(()=> {func.apply(this,args);}, timeout);
    };
}

function elimina(){
    /*Swal.fire({timer:3000,
        position: "top-end",
        text: "SE HA ELIMINADO CORRECTAMENTE",
        icon: "success",
        showConfirmButton: false,
    });*/
    Swal.fire({
        title: "Are you sure?",
        text: "You won't be able to revert this!",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Yes, delete it!"
      }).then((result) => {
        if (result.isConfirmed) {
          Swal.fire({
            title: "Deleted!",
            text: "Your file has been deleted.",
            icon: "success"
          });
        }
      });
    

}

function actualizarCuri(){
    Swal.fire({timer:3000,
        title:"Realizado!!",
        text: "Se ha actualizado Correctamente",
        icon: "success"
    });
    

}

function actualizar(){
    Swal.fire({timer:3000,
        position: "top-end",
        text: "SE HA ELIMINADO CORRECTAMENTE",
        icon: "success",
        showConfirmButton: false,
    });
    

}
    