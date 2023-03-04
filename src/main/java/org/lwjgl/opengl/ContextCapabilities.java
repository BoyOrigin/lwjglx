package org.lwjgl.opengl;

import org.lwjgl.system.LWJGLXHelper;

import java.lang.reflect.Field;

public class ContextCapabilities {
	
	org.lwjgl.opengl.GLCapabilities cap = org.lwjgl.opengl.GL.createCapabilities();
	
	public ContextCapabilities() {

		Field[] fields = org.lwjgl.opengl.GLCapabilities.class.getFields();

		for (Field field : fields) {

			String name = field.getName();

			if (name.startsWith("GL_") || name.startsWith("OpenGL")) {

				try {
					boolean value = field.getBoolean(cap);

					Field f = this.getClass().getField(name);
					f.setBoolean(this, value);
				} catch (Exception ignored) {}
			}
		}

		if (LWJGLXHelper.assumeGlExtensions) {
			this.GL_EXT_texture_rectangle |= this.GL_ARB_texture_rectangle;
		}
	}

	public boolean GL_AMD_blend_minmax_factor;
	public boolean GL_AMD_conservative_depth;
	public boolean GL_AMD_debug_output;
	public boolean GL_AMD_depth_clamp_separate;
	public boolean GL_AMD_draw_buffers_blend;
	public boolean GL_AMD_interleaved_elements;
	public boolean GL_AMD_multi_draw_indirect;
	public boolean GL_AMD_name_gen_delete;
	public boolean GL_AMD_performance_monitor;
	public boolean GL_AMD_pinned_memory;
	public boolean GL_AMD_query_buffer_object;
	public boolean GL_AMD_sample_positions;
	public boolean GL_AMD_seamless_cubemap_per_texture;
	public boolean GL_AMD_shader_atomic_counter_ops;
	public boolean GL_AMD_shader_stencil_export;
	public boolean GL_AMD_shader_trinary_minmax;
	public boolean GL_AMD_sparse_texture;
	public boolean GL_AMD_stencil_operation_extended;
	public boolean GL_AMD_texture_texture4;
	public boolean GL_AMD_transform_feedback3_lines_triangles;
	public boolean GL_AMD_vertex_shader_layer;
	public boolean GL_AMD_vertex_shader_tessellator;
	public boolean GL_AMD_vertex_shader_viewport_index;
	public boolean GL_APPLE_aux_depth_stencil;
	public boolean GL_APPLE_client_storage;
	public boolean GL_APPLE_element_array;
	public boolean GL_APPLE_fence;
	public boolean GL_APPLE_float_pixels;
	public boolean GL_APPLE_flush_buffer_range;
	public boolean GL_APPLE_object_purgeable;
	public boolean GL_APPLE_packed_pixels;
	public boolean GL_APPLE_rgb_422;
	public boolean GL_APPLE_row_bytes;
	public boolean GL_APPLE_texture_range;
	public boolean GL_APPLE_vertex_array_object;
	public boolean GL_APPLE_vertex_array_range;
	public boolean GL_APPLE_vertex_program_evaluators;
	public boolean GL_APPLE_ycbcr_422;
	public boolean GL_ARB_ES2_compatibility;
	public boolean GL_ARB_ES3_compatibility;
	public boolean GL_ARB_arrays_of_arrays;
	public boolean GL_ARB_base_instance;
	public boolean GL_ARB_bindless_texture;
	public boolean GL_ARB_blend_func_extended;
	public boolean GL_ARB_buffer_storage;
	public boolean GL_ARB_cl_event;
	public boolean GL_ARB_clear_buffer_object;
	public boolean GL_ARB_clear_texture;
	public boolean GL_ARB_color_buffer_float;
	public boolean GL_ARB_compatibility;
	public boolean GL_ARB_compressed_texture_pixel_storage;
	public boolean GL_ARB_compute_shader;
	public boolean GL_ARB_compute_variable_group_size;
	public boolean GL_ARB_conservative_depth;
	public boolean GL_ARB_copy_buffer;
	public boolean GL_ARB_copy_image;
	public boolean GL_ARB_debug_output;
	public boolean GL_ARB_depth_buffer_float;
	public boolean GL_ARB_depth_clamp;
	public boolean GL_ARB_depth_texture;
	public boolean GL_ARB_draw_buffers;
	public boolean GL_ARB_draw_buffers_blend;
	public boolean GL_ARB_draw_elements_base_vertex;
	public boolean GL_ARB_draw_indirect;
	public boolean GL_ARB_draw_instanced;
	public boolean GL_ARB_enhanced_layouts;
	public boolean GL_ARB_explicit_attrib_location;
	public boolean GL_ARB_explicit_uniform_location;
	public boolean GL_ARB_fragment_coord_conventions;
	public boolean GL_ARB_fragment_layer_viewport;
	public boolean GL_ARB_fragment_program;
	public boolean GL_ARB_fragment_program_shadow;
	public boolean GL_ARB_fragment_shader;
	public boolean GL_ARB_framebuffer_no_attachments;
	public boolean GL_ARB_framebuffer_object;
	public boolean GL_ARB_framebuffer_sRGB;
	public boolean GL_ARB_geometry_shader4;
	public boolean GL_ARB_get_program_binary;
	public boolean GL_ARB_gpu_shader5;
	public boolean GL_ARB_gpu_shader_fp64;
	public boolean GL_ARB_half_float_pixel;
	public boolean GL_ARB_half_float_vertex;
	public boolean GL_ARB_imaging;
	public boolean GL_ARB_indirect_parameters;
	public boolean GL_ARB_instanced_arrays;
	public boolean GL_ARB_internalformat_query;
	public boolean GL_ARB_internalformat_query2;
	public boolean GL_ARB_invalidate_subdata;
	public boolean GL_ARB_map_buffer_alignment;
	public boolean GL_ARB_map_buffer_range;
	public boolean GL_ARB_matrix_palette;
	public boolean GL_ARB_multi_bind;
	public boolean GL_ARB_multi_draw_indirect;
	public boolean GL_ARB_multisample;
	public boolean GL_ARB_multitexture;
	public boolean GL_ARB_occlusion_query;
	public boolean GL_ARB_occlusion_query2;
	public boolean GL_ARB_pixel_buffer_object;
	public boolean GL_ARB_point_parameters;
	public boolean GL_ARB_point_sprite;
	public boolean GL_ARB_program_interface_query;
	public boolean GL_ARB_provoking_vertex;
	public boolean GL_ARB_query_buffer_object;
	public boolean GL_ARB_robust_buffer_access_behavior;
	public boolean GL_ARB_robustness;
	public boolean GL_ARB_robustness_isolation;
	public boolean GL_ARB_sample_shading;
	public boolean GL_ARB_sampler_objects;
	public boolean GL_ARB_seamless_cube_map;
	public boolean GL_ARB_seamless_cubemap_per_texture;
	public boolean GL_ARB_separate_shader_objects;
	public boolean GL_ARB_shader_atomic_counters;
	public boolean GL_ARB_shader_bit_encoding;
	public boolean GL_ARB_shader_draw_parameters;
	public boolean GL_ARB_shader_group_vote;
	public boolean GL_ARB_shader_image_load_store;
	public boolean GL_ARB_shader_image_size;
	public boolean GL_ARB_shader_objects;
	public boolean GL_ARB_shader_precision;
	public boolean GL_ARB_shader_stencil_export;
	public boolean GL_ARB_shader_storage_buffer_object;
	public boolean GL_ARB_shader_subroutine;
	public boolean GL_ARB_shader_texture_lod;
	public boolean GL_ARB_shading_language_100;
	public boolean GL_ARB_shading_language_420pack;
	public boolean GL_ARB_shading_language_include;
	public boolean GL_ARB_shading_language_packing;
	public boolean GL_ARB_shadow;
	public boolean GL_ARB_shadow_ambient;
	public boolean GL_ARB_sparse_texture;
	public boolean GL_ARB_stencil_texturing;
	public boolean GL_ARB_sync;
	public boolean GL_ARB_tessellation_shader;
	public boolean GL_ARB_texture_border_clamp;
	public boolean GL_ARB_texture_buffer_object;
	public boolean GL_ARB_texture_buffer_object_rgb32;
	public boolean GL_ARB_texture_buffer_range;
	public boolean GL_ARB_texture_compression;
	public boolean GL_ARB_texture_compression_bptc;
	public boolean GL_ARB_texture_compression_rgtc;
	public boolean GL_ARB_texture_cube_map;
	public boolean GL_ARB_texture_cube_map_array;
	public boolean GL_ARB_texture_env_add;
	public boolean GL_ARB_texture_env_combine;
	public boolean GL_ARB_texture_env_crossbar;
	public boolean GL_ARB_texture_env_dot3;
	public boolean GL_ARB_texture_float;;
	public boolean GL_ARB_texture_gather;
	public boolean GL_ARB_texture_mirror_clamp_to_edge;
	public boolean GL_ARB_texture_mirrored_repeat;
	public boolean GL_ARB_texture_multisample;
	public boolean GL_ARB_texture_non_power_of_two;
	public boolean GL_ARB_texture_query_levels;
	public boolean GL_ARB_texture_query_lod;
	public boolean GL_ARB_texture_rectangle;
	public boolean GL_ARB_texture_rg;
	public boolean GL_ARB_texture_rgb10_a2ui;
	public boolean GL_ARB_texture_stencil8;
	public boolean GL_ARB_texture_storage;
	public boolean GL_ARB_texture_storage_multisample;
	public boolean GL_ARB_texture_swizzle;
	public boolean GL_ARB_texture_view;
	public boolean GL_ARB_timer_query;
	public boolean GL_ARB_transform_feedback2;
	public boolean GL_ARB_transform_feedback3;
	public boolean GL_ARB_transform_feedback_instanced;
	public boolean GL_ARB_transpose_matrix;
	public boolean GL_ARB_uniform_buffer_object;
	public boolean GL_ARB_vertex_array_bgra;
	public boolean GL_ARB_vertex_array_object;
	public boolean GL_ARB_vertex_attrib_64bit;
	public boolean GL_ARB_vertex_attrib_binding;
	public boolean GL_ARB_vertex_blend;
	public boolean GL_ARB_vertex_buffer_object;
	public boolean GL_ARB_vertex_program;
	public boolean GL_ARB_vertex_shader;
	public boolean GL_ARB_vertex_type_10f_11f_11f_rev;
	public boolean GL_ARB_vertex_type_2_10_10_10_rev;
	public boolean GL_ARB_viewport_array;
	public boolean GL_ARB_window_pos;
	public boolean GL_ATI_draw_buffers;
	public boolean GL_ATI_element_array;
	public boolean GL_ATI_envmap_bumpmap;
	public boolean GL_ATI_fragment_shader;
	public boolean GL_ATI_map_object_buffer;
	public boolean GL_ATI_meminfo;
	public boolean GL_ATI_pn_triangles;
	public boolean GL_ATI_separate_stencil;
	public boolean GL_ATI_shader_texture_lod;
	public boolean GL_ATI_text_fragment_shader;
	public boolean GL_ATI_texture_compression_3dc;
	public boolean GL_ATI_texture_env_combine3;
	public boolean GL_ATI_texture_float;
	public boolean GL_ATI_texture_mirror_once;
	public boolean GL_ATI_vertex_array_object;
	public boolean GL_ATI_vertex_attrib_array_object;
	public boolean GL_ATI_vertex_streams;
	public boolean GL_EXT_abgr;
	public boolean GL_EXT_bgra;
	public boolean GL_EXT_bindable_uniform;
	public boolean GL_EXT_blend_color;
	public boolean GL_EXT_blend_equation_separate;
	public boolean GL_EXT_blend_func_separate;
	public boolean GL_EXT_blend_minmax;
	public boolean GL_EXT_blend_subtract;
	public boolean GL_EXT_Cg_shader;
	public boolean GL_EXT_compiled_vertex_array;
	public boolean GL_EXT_depth_bounds_test;
	public boolean GL_EXT_direct_state_access;
	public boolean GL_EXT_draw_buffers2;
	public boolean GL_EXT_draw_instanced;
	public boolean GL_EXT_draw_range_elements;
	public boolean GL_EXT_fog_coord;
	public boolean GL_EXT_framebuffer_blit;
	public boolean GL_EXT_framebuffer_multisample;
	public boolean GL_EXT_framebuffer_multisample_blit_scaled;
	public boolean GL_EXT_framebuffer_object;
	public boolean GL_EXT_framebuffer_sRGB;
	public boolean GL_EXT_geometry_shader4;
	public boolean GL_EXT_gpu_program_parameters;
	public boolean GL_EXT_gpu_shader4;
	public boolean GL_EXT_multi_draw_arrays;
	public boolean GL_EXT_packed_depth_stencil;
	public boolean GL_EXT_packed_float;
	public boolean GL_EXT_packed_pixels;
	public boolean GL_EXT_paletted_texture;
	public boolean GL_EXT_pixel_buffer_object;
	public boolean GL_EXT_point_parameters;
	public boolean GL_EXT_provoking_vertex;
	public boolean GL_EXT_rescale_normal;
	public boolean GL_EXT_secondary_color;
	public boolean GL_EXT_separate_shader_objects;
	public boolean GL_EXT_separate_specular_color;
	public boolean GL_EXT_shader_image_load_store;
	public boolean GL_EXT_shadow_funcs;
	public boolean GL_EXT_shared_texture_palette;
	public boolean GL_EXT_stencil_clear_tag;
	public boolean GL_EXT_stencil_two_side;
	public boolean GL_EXT_stencil_wrap;
	public boolean GL_EXT_texture_3d;
	public boolean GL_EXT_texture_array;
	public boolean GL_EXT_texture_buffer_object;
	public boolean GL_EXT_texture_compression_latc;
	public boolean GL_EXT_texture_compression_rgtc;
	public boolean GL_EXT_texture_compression_s3tc;
	public boolean GL_EXT_texture_env_combine;
	public boolean GL_EXT_texture_env_dot3;
	public boolean GL_EXT_texture_filter_anisotropic;
	public boolean GL_EXT_texture_integer;
	public boolean GL_EXT_texture_lod_bias;
	public boolean GL_EXT_texture_mirror_clamp;
	public boolean GL_EXT_texture_rectangle;
	public boolean GL_EXT_texture_sRGB;
	public boolean GL_EXT_texture_sRGB_decode;
	public boolean GL_EXT_texture_shared_exponent;
	public boolean GL_EXT_texture_snorm;
	public boolean GL_EXT_texture_swizzle;
	public boolean GL_EXT_timer_query;
	public boolean GL_EXT_transform_feedback;
	public boolean GL_EXT_vertex_array_bgra;
	public boolean GL_EXT_vertex_attrib_64bit;
	public boolean GL_EXT_vertex_shader;
	public boolean GL_EXT_vertex_weighting;
	public boolean OpenGL11;
	public boolean OpenGL12;
	public boolean OpenGL13;
	public boolean OpenGL14;
	public boolean OpenGL15;
	public boolean OpenGL20;
	public boolean OpenGL21;
	public boolean OpenGL30;
	public boolean OpenGL31;
	public boolean OpenGL32;
	public boolean OpenGL33;
	public boolean OpenGL40;
	public boolean OpenGL41;
	public boolean OpenGL42;
	public boolean OpenGL43;
	public boolean OpenGL44;
	public boolean GL_GREMEDY_frame_terminator;
	public boolean GL_GREMEDY_string_marker;
	public boolean GL_HP_occlusion_test;
	public boolean GL_IBM_rasterpos_clip;
	public boolean GL_INTEL_map_texture;
	public boolean GL_KHR_debug;
	public boolean GL_KHR_texture_compression_astc_ldr;
	public boolean GL_NVX_gpu_memory_info;
	public boolean GL_NV_bindless_multi_draw_indirect;
	public boolean GL_NV_bindless_texture;
	public boolean GL_NV_blend_equation_advanced;
	public boolean GL_NV_blend_square;
	public boolean GL_NV_compute_program5;
	public boolean GL_NV_conditional_render;
	public boolean GL_NV_copy_depth_to_color;
	public boolean GL_NV_copy_image;
	public boolean GL_NV_deep_texture3D;
	public boolean GL_NV_depth_buffer_float;
	public boolean GL_NV_depth_clamp;
	public boolean GL_NV_draw_texture;
	public boolean GL_NV_evaluators;
	public boolean GL_NV_explicit_multisample;
	public boolean GL_NV_fence;
	public boolean GL_NV_float_buffer;
	public boolean GL_NV_fog_distance;
	public boolean GL_NV_fragment_program;
	public boolean GL_NV_fragment_program2;
	public boolean GL_NV_fragment_program4;
	public boolean GL_NV_fragment_program_option;
	public boolean GL_NV_framebuffer_multisample_coverage;
	public boolean GL_NV_geometry_program4;
	public boolean GL_NV_geometry_shader4;
	public boolean GL_NV_gpu_program4;
	public boolean GL_NV_gpu_program5;
	public boolean GL_NV_gpu_program5_mem_extended;
	public boolean GL_NV_gpu_shader5;
	public boolean GL_NV_half_float;
	public boolean GL_NV_light_max_exponent;
	public boolean GL_NV_multisample_coverage;
	public boolean GL_NV_multisample_filter_hint;
	public boolean GL_NV_occlusion_query;
	public boolean GL_NV_packed_depth_stencil;
	public boolean GL_NV_parameter_buffer_object;
	public boolean GL_NV_parameter_buffer_object2;
	public boolean GL_NV_path_rendering;
	public boolean GL_NV_pixel_data_range;
	public boolean GL_NV_point_sprite;
	public boolean GL_NV_present_video;
	public boolean GL_NV_primitive_restart;
	public boolean GL_NV_register_combiners;
	public boolean GL_NV_register_combiners2;
	public boolean GL_NV_shader_atomic_counters;
	public boolean GL_NV_shader_atomic_float;
	public boolean GL_NV_shader_buffer_load;
	public boolean GL_NV_shader_buffer_store;
	public boolean GL_NV_shader_storage_buffer_object;
	public boolean GL_NV_tessellation_program5;
	public boolean GL_NV_texgen_reflection;
	public boolean GL_NV_texture_barrier;
	public boolean GL_NV_texture_compression_vtc;
	public boolean GL_NV_texture_env_combine4;
	public boolean GL_NV_texture_expand_normal;
	public boolean GL_NV_texture_multisample;
	public boolean GL_NV_texture_rectangle;
	public boolean GL_NV_texture_shader;
	public boolean GL_NV_texture_shader2;
	public boolean GL_NV_texture_shader3;
	public boolean GL_NV_transform_feedback;
	public boolean GL_NV_transform_feedback2;
	public boolean GL_NV_vertex_array_range;
	public boolean GL_NV_vertex_array_range2;
	public boolean GL_NV_vertex_attrib_integer_64bit;
	public boolean GL_NV_vertex_buffer_unified_memory;
	public boolean GL_NV_vertex_program;
	public boolean GL_NV_vertex_program1_1;
	public boolean GL_NV_vertex_program2;
	public boolean GL_NV_vertex_program2_option;
	public boolean GL_NV_vertex_program3;
	public boolean GL_NV_vertex_program4;
	public boolean GL_NV_video_capture;
	public boolean GL_SGIS_generate_mipmap;
	public boolean GL_SGIS_texture_lod;
	public boolean GL_SUN_slice_accum;

	public static void main(String[] arg) {
		System.out.println("START!");
		new ContextCapabilities();
	}

}
