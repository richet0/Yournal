export interface User{
    userName:string;
    email:string;
    rol?:string;    
}

export interface RegistroDto{
    nombre:string;
    email:string;
    password:string;
}

export interface LoginDto{
    email:string;
    password:string;
}