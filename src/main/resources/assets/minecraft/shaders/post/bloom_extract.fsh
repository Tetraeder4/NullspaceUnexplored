#version 330

uniform sampler2D InSampler;

in vec2 texCoord;

layout(std140) uniform SamplerInfo {
    vec2 OutSize;
    vec2 InSize;
};

layout(std140) uniform BloomConfig {
    float Threshold;
};

out vec4 fragColor;

void main() {
    vec3 color = texture(InSampler, texCoord).rgb;
    //grayscale bias
    float maxChannel = max(color.r, max(color.g, color.b));
    float minChannel = min(color.r, min(color.g, color.b));

    float grayscale = 1.0 - (maxChannel - minChannel);

    float luminance = dot(color, vec3(0.299, 0.587, 0.114));

    float brightness = mix(
            maxChannel, luminance, grayscale * 0.5);

    float bloomFactor = smoothstep(
            Threshold,
            Threshold + 0.2,
            brightness
    );

    fragColor = vec4(color * bloomFactor, 1.0);
}