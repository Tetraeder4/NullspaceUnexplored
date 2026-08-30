#version 330

#moj_import <minecraft:globals.glsl>

uniform sampler2D InSampler;

in vec2 texCoord;

out vec4 fragColor;

layout(std140) uniform SamplerInfo {
    vec2 OutSize;
    vec2 InSize;
};

vec3 CA(sampler2D tex, vec2 uv)
{
    float strength = 0.01;
    float r = texture(tex, uv + vec2(0.0, strength)).r;
    float g = texture(tex, uv).g;
    float b = texture(tex, uv + vec2(0.0, -strength)).b;
    return vec3(r,g,b);
}

vec3 Sharpen(sampler2D tex, vec2 uv)
{
    float strength = 0.0075;
    vec3 tl = CA(tex, uv + vec2(-strength, strength)).rgb;
    vec3 tm = CA(tex, uv + vec2(0.0, strength)).rgb;
    vec3 tr = CA(tex, uv + vec2(strength, strength)).rgb;
    vec3 ml = CA(tex, uv + vec2(-strength, 0.0)).rgb;
    vec3 mr = CA(tex, uv + vec2(strength, 0.0)).rgb;
    vec3 bl = CA(tex, uv + vec2(-strength, -strength)).rgb;
    vec3 bm = CA(tex, uv + vec2(0.0, -strength)).rgb;
    vec3 br = CA(tex, uv + vec2(strength, -strength)).rgb;
    vec3 final = (tl+tm+tr+ml+mr+bl+bm+br)/8.0;
    final = mix(texture(tex, uv).rgb, final, -2.0);
    return final;
}

float random(vec2 p) {
    return fract(sin(dot(p, vec2(127.1, 311.7))) * 256.0);
}

vec3 rgbNoise(vec2 uv) {
    vec2 texel = floor(uv * vec2(256.0, InSize.y));
    return vec3(
            random(texel + vec2(0.0, 0.0)),
            random(texel + vec2(17.3, 41.7)),
            random(texel + vec2(83.1, 29.4))
    );
}

vec3 Blur(sampler2D tex, vec2 uv)
{
    float strength = 0.001;
    vec3 tl = Sharpen(tex, uv + vec2(-strength, strength)).rgb;
    vec3 tm = Sharpen(tex, uv + vec2(0.0, strength)).rgb;
    vec3 tr = Sharpen(tex, uv + vec2(strength, strength)).rgb;
    vec3 ml = Sharpen(tex, uv + vec2(-strength, 0.0)).rgb;
    vec3 mr = Sharpen(tex, uv + vec2(strength, 0.0)).rgb;
    vec3 bl = Sharpen(tex, uv + vec2(-strength, -strength)).rgb;
    vec3 bm = Sharpen(tex, uv + vec2(0.0, -strength)).rgb;
    vec3 br = Sharpen(tex, uv + vec2(strength, -strength)).rgb;
    return (tl+tm+tr+ml+mr+bl+bm+br)/8.0;
}

vec2 DistortUV(vec2 uv, float time)
{
    float offset = rgbNoise(vec2(time, uv.y * 0.5)).r;
    offset -= 0.5;
    offset *= 2.0;
    offset *= 0.0005;
    uv.x += offset;
    return uv;
}

vec3 ColorCorrect(vec3 color)
{
    color -= 0.02;
    color = color * vec3(1.0, 1.0, 0.5);
    color = clamp(color, vec3(0.0), vec3(1.0));
    color *= 1.1;
    return color;
}

/*vec3 ColorCorrect(vec3 color)
{
    float brightness = 1.2;
    float saturation = 0.9;
    float contrast = 0.8;

    // Brightness
    color -= 0.1;
    color *= brightness;

    // Saturation
    float luminance = dot(color, vec3(0.299, 0.587, 0.114));
    color = mix(vec3(luminance), color, saturation);

    // Contrast
    color = (color - 0.5) * contrast + 0.5;

    color = color * vec3(1.5, 1.5, 0.6);

    color = pow(color, vec3(1.0));

    color /= 1.5;

    return clamp(color, vec3(0.0), vec3(1.0));
}*/

vec3 Finalize(vec3 color, vec2 uv, float time)
{
    float bars = rgbNoise(vec2(time, uv.y * 0.1)).r;
    color += vec3(bars) * vec3(0.05, 0.01, 0.005) * vec3(0.5);
    float n = rgbNoise(uv * 0.45 + (time * rgbNoise(vec2(time)).g)).b;
    n *= 0.05;
    return (color * n) + color / 1.1;
}



void main()
{
    float t = fract(GameTime * 1200.0) / 8.0;
    vec2 uv = DistortUV(texCoord, t);

    vec3 color = Blur(InSampler, uv);
    color = ColorCorrect(color);
    color = Finalize(color, uv, t);

    fragColor = vec4(color, 1.0);
}