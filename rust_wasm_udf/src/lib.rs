use extism_pdk::*;
use serde::{Deserialize, Serialize};

pub mod co {
    pub mod gaffe {
        pub mod proto {
            include!(concat!(env!("OUT_DIR"), "/co.gaffe.proto.rs"));
        }
    }
}

use co::gaffe::proto;
use co::gaffe::proto::{literal::LiteralType, Literal};

#[plugin_fn]
pub fn add_two(args: Prost<proto::UdfArgs>) -> FnResult<Prost<Literal>> {
    let udfargs = args.0;
    
    if udfargs.args.len() == 2 {
        let first_arg = udfargs.args.first().unwrap();
        let second_arg = udfargs.args.last().unwrap();

        match (&first_arg.literal_type, &second_arg.literal_type) {
            (Some(LiteralType::Integer(first)), Some(LiteralType::Integer(second))) => {
                let res = Literal { literal_type: Some(LiteralType::Integer(first + second))};
                Ok(Prost(res))
            }
            _ => {
                Err(WithReturnCode(anyhow::format_err!("Invalid arguments {:?}, {:?}", first_arg.literal_type, second_arg.literal_type), -1)) 
            }
        }
    } else {
        Err(WithReturnCode(anyhow::format_err!("Invalid argument count: {}", udfargs.args.len()), -1))
    }
}

/*#[plugin_fn]
pub fn add_two_str(args: Prost<proto::UdfArgs>) -> FnResult<String> {
    let udfargs = args.0;

    if udfargs.args.len() == 2 {
        let first_arg = udfargs.args.first().unwrap();
        let second_arg = udfargs.args.last().unwrap();

        

        match (&first_arg.literal_type, &second_arg.literal_type) {
            (Some(LiteralType::Integer(first)), Some(LiteralType::Integer(second))) => {
                //let res = Literal { literal_type: Some(LiteralType::Integer(first + second))};
                Ok(format!("{} + {}", first, second))
                //Ok(Prost(res))
            }
            _ => {
                Ok(format!("The lits are {:?} and {:?}", first_arg, second_arg))
                //Err(WithReturnCode(anyhow::format_err!("Invalid arguments {:?}, {:?}", first_arg.literal_type, second_arg.literal_type), 500)) 
            }
        }
    } else {
        Ok(format!("Invalid argument count: {}", udfargs.args.len()))
        //Err(WithReturnCode(anyhow::format_err!("Invalid argument count: {}", udfargs.args.len()), 500))
    }
}*/
