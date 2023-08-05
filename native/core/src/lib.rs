#![allow(unused)]
#![feature(portable_simd)]
#![feature(cell_leak)]

mod collections;
mod ffi;
mod graph;
mod jni;
mod math;
mod mem;
mod panic;

pub fn test<const VAL: usize>() {
    test::<{ VAL - 1 }>()
}
